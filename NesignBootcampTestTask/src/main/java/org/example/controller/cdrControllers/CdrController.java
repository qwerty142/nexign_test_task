package org.example.controller.cdrControllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.controller.dto.CdrDto;
import org.example.controller.dto.UdrDTO;
import org.example.controller.dto.UserDto;
import org.example.controller.mapper.ObjectMapper;
import org.example.generator.ReportGenerator;
import org.example.service.CdrServices.ICdrService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cdrs")
@AllArgsConstructor
public class CdrController {

    private final ICdrService cdrService;

    @PostMapping
    public CdrDto addCdr(@RequestBody CdrDto cdrDto) {
        return ObjectMapper.modelToCdrDto(cdrService.createCdr(ObjectMapper.cdrDtoToModel(cdrDto)));
    }

    @GetMapping
    public CdrDto findCdrById(@RequestParam long cdrId) {
        return ObjectMapper.modelToCdrDto(cdrService.getCdrByCdrId(cdrId));
    }

    @PatchMapping
    public CdrDto updateCdr(@RequestBody CdrDto cdrDto) {
        return ObjectMapper.modelToCdrDto(cdrService.createCdr(ObjectMapper.cdrDtoToModel(cdrDto)));
    }

    @GetMapping("/udr/period")
    public UdrDTO generateUdrPeriod(@RequestParam String number, @RequestParam String periodType) {
        return ObjectMapper.cdrsToUdrDto(cdrService.getAllCdrsByNumber(number, periodType));
    }

    @GetMapping("/udr/duration")
    public UdrDTO generateUdrDuration(@RequestParam String number, @RequestParam Date startDate, @RequestParam Date endDate) {
        return ObjectMapper.cdrsToUdrDto(cdrService.findAllByNumberAndPeriod(number, startDate, endDate));
    }

    @GetMapping("/udr/month")
    public List<UdrDTO> generateUdrMonth(@RequestParam String month, @RequestParam int year) {
        return ObjectMapper.cdrsToUdrDtos(cdrService.findAllByMonthAndYear(month, year));
    }

    @PostMapping("report")
    public String generateReport(@RequestParam String number, @RequestParam Date startDate, @RequestParam Date endDate) {
        var res = cdrService.findAllByNumberAndPeriod(number, startDate, endDate);
        return ReportGenerator.saveCdrToFile(ObjectMapper.cdrsModelsTocdrDtos(res));
    }

}
