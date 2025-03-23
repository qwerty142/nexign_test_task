package org.example.service.UserServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.repository.repositories.UserRepository;
import org.example.service.Mappers.ObjectMapper;
import org.example.service.model.UserModel;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    @Override
    @Transactional
    public UserModel getUserByPhoneNumber(String phoneNumber) {
        return ObjectMapper.DbToUserModel(userRepository.findById(phoneNumber).get());
    }

    @Override
    @Transactional
    public UserModel createUser(UserModel userModel) {

        var result = userRepository.save(ObjectMapper.UserModelToDb(userModel));

        return ObjectMapper.DbToUserModel(result);
    }

    @Override
    @Transactional
    public UserModel updateUser(UserModel userModel) {
        return ObjectMapper.DbToUserModel(userRepository.save(ObjectMapper.UserModelToDb(userModel)));
    }

    @Override
    @Transactional
    public boolean deleteUser(UserModel userModel) {

        return true;
    }
}
