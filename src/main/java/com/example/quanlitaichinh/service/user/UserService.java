package com.example.quanlitaichinh.service.user;

import com.example.quanlitaichinh.model.User;
import com.example.quanlitaichinh.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IGeneralService<User> {
    UserDetailsService userDetailsService();

}
