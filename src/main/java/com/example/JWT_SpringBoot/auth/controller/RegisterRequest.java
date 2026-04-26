package com.example.JWT_SpringBoot.auth.controller;

public record RegisterRequest (
        String email,
        String password,
        String name
){}

