package com.example.JWT_SpringBoot.auth.controller;

public record LoginRequest (
        String email,
        String password
){}
