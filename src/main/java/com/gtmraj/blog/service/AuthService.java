package com.gtmraj.blog.service;

import com.gtmraj.blog.payload.LoginDto;
import com.gtmraj.blog.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
