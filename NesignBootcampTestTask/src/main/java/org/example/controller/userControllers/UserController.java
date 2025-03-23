package org.example.controller.userControllers;

import lombok.RequiredArgsConstructor;
import org.example.controller.dto.UserDto;
import org.example.controller.mapper.ObjectMapper;
import org.example.service.UserServices.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping
    public UserDto addUser(@RequestBody UserDto userDto) {
        return ObjectMapper.userModelToUserDto(userService.createUser(ObjectMapper.userDtoToModel(userDto)));
    }

    @GetMapping
    public UserDto findUserById(@RequestParam("number") String number) {
        return ObjectMapper.userModelToUserDto(userService.getUserByPhoneNumber(number));
    }

    @PatchMapping
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return ObjectMapper.userModelToUserDto(userService.updateUser(ObjectMapper.userDtoToModel(userDto)));
    }

}
