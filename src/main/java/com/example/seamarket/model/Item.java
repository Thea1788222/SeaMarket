package com.example.seamarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Item {
    private int id;
    private int sellerId;
    private String title;
    private String description;
    private BigDecimal price;
    private String status; // ACTIVE / SOLD / DELETED
    private LocalDateTime createdAt;
}
