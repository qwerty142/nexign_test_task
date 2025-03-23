package org.example.service.UserServices;

import org.example.service.model.UserModel;

public interface IUserService {
    public UserModel getUserByPhoneNumber(String phoneNumber);

    public UserModel createUser(UserModel userModel);

    public UserModel updateUser(UserModel userModel);

    public boolean deleteUser(UserModel userModel);
}
