package com.demo.project.dto;

import lombok.*;
import sun.util.resources.ga.LocaleNames_ga;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
