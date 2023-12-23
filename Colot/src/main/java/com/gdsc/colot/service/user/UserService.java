package com.gdsc.colot.service.user;


import com.gdsc.colot.controller.dto.request.SignUpRequestDto;

public interface UserService {

    void saveUser(SignUpRequestDto signUpRequestDto);

}
