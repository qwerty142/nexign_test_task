package org.example.service.Mappers;

import org.example.repository.tables.Cdr;
import org.example.repository.tables.User;
import org.example.service.model.CdrModel;
import org.example.service.model.UserModel;

public final class ObjectMapper {

    private ObjectMapper() {}


    public static User UserModelToDb(UserModel userModel) {
        return User.builder()
                .name(userModel.getName())
                .number(userModel.getNumber())
                .build();
    }


    public static UserModel DbToUserModel(User user) {
        return UserModel.builder()
                .name(user.getName())
                .number(user.getNumber())
                .build();
    }


    public static CdrModel DbToCdrModel(Cdr cdrModel) {
        return CdrModel.builder()
                .id(cdrModel.getId())
                .call_type(cdrModel.getCall_type())
                .income_number(cdrModel.getIncome_number())
                .outcome_number(cdrModel.getOutcome_number())
                .begin(cdrModel.getBegin_call())
                .end(cdrModel.getEnd_call())
                .build();
    }


    public static Cdr CdrModelToDb(CdrModel cdrModel) {
        return Cdr.builder()
                .id(cdrModel.getId())
                .call_type(cdrModel.getCall_type())
                .income_number(cdrModel.getIncome_number())
                .outcome_number(cdrModel.getOutcome_number())
                .begin_call(cdrModel.getBegin())
                .end_call(cdrModel.getEnd())
                .build();
    }
}
