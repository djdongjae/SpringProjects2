package com.groom.cookiehousedemo.repository;

import com.groom.cookiehousedemo.domain.user.SocialType;
import com.groom.cookiehousedemo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsBySocialIdAndSocialType(String socialId, SocialType socialType);
    Optional<User> findBySocialIdAndSocialType(String socialId, SocialType socialType);
}
