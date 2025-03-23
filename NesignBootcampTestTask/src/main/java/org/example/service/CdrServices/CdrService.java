package org.example.service.CdrServices;

import lombok.AllArgsConstructor;
import org.example.repository.repositories.CdrRepository;
import org.example.service.Mappers.ObjectMapper;
import org.example.service.model.CdrModel;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@ComponentScan
public class CdrService implements ICdrService {

    private final CdrRepository cdrRepository;

    @Override
    public CdrModel getCdrByCdrId(long cdrId) {
        return ObjectMapper.DbToCdrModel(cdrRepository.findById(cdrId).get());
    }

    @Override
    public CdrModel createCdr(CdrModel cdrModel) {
        return ObjectMapper.DbToCdrModel(cdrRepository.save(ObjectMapper.CdrModelToDb(cdrModel)));
    }

    @Override
    public CdrModel updateCdr(CdrModel cdrModel) {
        return ObjectMapper.DbToCdrModel(cdrRepository.save(ObjectMapper.CdrModelToDb(cdrModel)));
    }

    @Override
    public CdrModel deleteCdr(CdrModel cdrModel) {
        cdrRepository.delete(ObjectMapper.CdrModelToDb(cdrModel));

        return CdrModel.builder().build();
    }

    @Override
    public List<CdrModel> getAllCdrsByNumber(String number, String periodType) {

        Date startDate = calculateStartDate(periodType);
        var res = cdrRepository.findAllByNumber(number, startDate);
        return res.stream()
                .map(ObjectMapper::DbToCdrModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CdrModel> findAllByNumberAndPeriod(String number, Date startDate, Date endDate) {
        var res = cdrRepository.findAllByNumberAndPeriod(number, startDate, endDate);
        return res.stream()
                .map(ObjectMapper::DbToCdrModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<CdrModel> findAllByMonthAndYear(String monthName, int year) {
        int monthNumber = getMonthNumber(monthName);
        return cdrRepository.findAllByMonthAndYear(monthNumber, year).stream()
                .map(ObjectMapper::DbToCdrModel)
                .collect(Collectors.toList());
    }

    private int getMonthNumber(String monthName) {
        try {
            return Month.valueOf(monthName.toUpperCase()).getValue();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Некорректное название месяца: " + monthName);
        }
    }

    private Date calculateStartDate(String periodType) {
        Calendar calendar = Calendar.getInstance();

        switch (periodType.toLowerCase()) {
            case "day":
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                break;
            case "week":
                calendar.add(Calendar.WEEK_OF_YEAR, -1);
                break;
            case "month":
                calendar.add(Calendar.MONTH, -1);
                break;
            default:
                throw new IllegalArgumentException("Invalid period type: " + periodType);
        }
        return calendar.getTime();
    }
}
