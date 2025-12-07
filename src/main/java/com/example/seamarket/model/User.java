package com.example.seamarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private int id;
    private String username;
    private String passwordHash;
    private String email;
    private LocalDateTime createdAt;
}
