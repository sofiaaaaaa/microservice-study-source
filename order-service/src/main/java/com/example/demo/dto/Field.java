package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Field  {
    private String type;
    private boolean optional;
    private String field;
}
