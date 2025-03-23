package org.example.service.CdrServices;

import org.example.service.model.CdrModel;

import java.util.Date;
import java.util.List;

public interface ICdrService {

    public CdrModel getCdrByCdrId(long cdrId);

    public CdrModel createCdr(CdrModel cdrModel);

    public CdrModel updateCdr(CdrModel cdrModel);

    public CdrModel deleteCdr(CdrModel cdrModel);

    public List<CdrModel> getAllCdrsByNumber(String number, String periodType);

    public List<CdrModel> findAllByNumberAndPeriod(String number, Date startDate, Date endDate);

    public List<CdrModel> findAllByMonthAndYear(String month, int year);
}
