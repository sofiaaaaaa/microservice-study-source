package com.example.demo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {
    private String productId;
    private String productName;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;
    private Integer stock;
    private Date createdAt;

    private String orderId;
}
