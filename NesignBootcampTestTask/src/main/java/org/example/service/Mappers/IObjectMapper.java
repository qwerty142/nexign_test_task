package org.example.service.Mappers;

import org.example.repository.tables.Cdr;
import org.example.repository.tables.User;
import org.example.service.model.CdrModel;
import org.example.service.model.UserModel;

public interface IObjectMapper {

    public User UserModelToDb(UserModel userModel);

    public UserModel DbToUserModel(User user);

    public CdrModel DbToCdrModel(Cdr cdrModel);

    public Cdr CdrModelToDb(CdrModel cdrModel);
}
