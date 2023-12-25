package com.gdsc.colot.service.user;

import com.gdsc.colot.controller.dto.request.SignUpRequestDto;
import com.gdsc.colot.domain.user.User;
import com.gdsc.colot.domain.user.UserType;
import com.gdsc.colot.exception.ErrorCode;
import com.gdsc.colot.exception.model.DuplicateUserException;
import com.gdsc.colot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(SignUpRequestDto signUpRequestDto) {
        checkDuplicateEmail(signUpRequestDto.getEmail());
        User user = User.builder()
                .username(signUpRequestDto.getEmail())
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .type(UserType.DEFAULT)
                .build();

        userRepository.save(user);
    }

    // 이메일 중복 체크 기능
    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email))
            throw new DuplicateUserException(ErrorCode.DUPLICATE_EMAIL_EXCEPTION, ErrorCode.DUPLICATE_EMAIL_EXCEPTION.getMessage());
    }

}
