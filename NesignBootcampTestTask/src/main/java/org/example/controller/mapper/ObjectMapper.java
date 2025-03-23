package org.example.controller.mapper;

import org.example.controller.dto.CdrDto;
import org.example.controller.dto.UdrDTO;
import org.example.controller.dto.UserDto;
import org.example.service.model.CdrModel;
import org.example.service.model.UserModel;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public final class ObjectMapper {

    public static CdrModel cdrDtoToModel(CdrDto cdrDto) {
        return CdrModel.builder()
                .id(cdrDto.getId())
                .call_type(cdrDto.getCall_type())
                .income_number(cdrDto.getIncome_number())
                .outcome_number(cdrDto.getOutcome_number())
                .begin(cdrDto.getBegin())
                .end(cdrDto.getEnd())
                .build();
    }

    public static CdrDto modelToCdrDto(CdrModel cdrDto) {
        return CdrDto.builder()
                .id(cdrDto.getId())
                .call_type(cdrDto.getCall_type())
                .income_number(cdrDto.getIncome_number())
                .outcome_number(cdrDto.getOutcome_number())
                .begin(cdrDto.getBegin())
                .end(cdrDto.getEnd())
                .build();
    }

    public static UserModel userDtoToModel(UserDto userDto) {
        return UserModel.builder()
                .number(userDto.getNumber())
                .name(userDto.getName())
                .build();
    }

    public static UserDto userModelToUserDto(UserModel userModel) {
        return UserDto.builder()
                .number(userModel.getNumber())
                .name(userModel.getName())
                .build();
    }

    public static UdrDTO cdrsToUdrDto(List<CdrModel> cdrModels) {

        var ent = cdrModels.get(0);
        String number;

        if (ent.getCall_type().equals(1)) {
            number = ent.getIncome_number().getNumber();
        } else {
            number = ent.getOutcome_number().getNumber();
        }

        long totalOutgoing = cdrModels.stream()
                .filter(cdr -> ((cdr.getCall_type().equals(2)) && cdr.getOutcome_number().getNumber().equals(number))) // Фильтруем исходящие
                .mapToLong(ObjectMapper::getCallDuration)
                .sum();

        long totalIngoing = cdrModels.stream()
                .filter(cdr -> ((cdr.getCall_type().equals(1)) && cdr.getIncome_number().getNumber().equals(number))) // Фильтруем входящие
                .mapToLong(ObjectMapper::getCallDuration)
                .sum();

        return new UdrDTO(number, totalIngoing, totalOutgoing);
    }

    private static long getCallDuration(CdrModel cdr) {
        return TimeUnit.MILLISECONDS.toSeconds(cdr.getEnd().getTime() - cdr.getBegin().getTime());
    }

    public static List<UdrDTO> cdrsToUdrDtos(List<CdrModel> cdrModels) {

        Map<String, UdrDTO> summaryMap = cdrModels.stream()
                .flatMap(cdr -> List.of(
                        new UdrDTO(
                                cdr.getOutcome_number().getNumber(),
                                Duration.between(cdr.getBegin().toInstant(), cdr.getEnd().toInstant()).getSeconds(),
                                0
                        ),
                        new UdrDTO(
                                cdr.getIncome_number().getNumber(),
                                0,
                                Duration.between(cdr.getBegin().toInstant(), cdr.getEnd().toInstant()).getSeconds()
                        )
                ).stream())
                .collect(Collectors.toMap(
                        UdrDTO::getNumber,
                        cs -> cs,
                        (cs1, cs2) -> new UdrDTO(
                                cs1.getNumber(),
                                cs1.getTotalOutcome() + cs2.getTotalOutcome(),
                                cs1.getTotalIncome() + cs2.getTotalIncome()
                        )
                ));

        return List.copyOf(summaryMap.values());

    }

    public static List<CdrDto> cdrsModelsTocdrDtos(List<CdrModel> cdrModels) {
        return cdrModels.stream()
                .map(ObjectMapper::modelToCdrDto)
                .collect(Collectors.toList());
    }

}
