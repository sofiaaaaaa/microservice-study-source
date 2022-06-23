package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;
}
