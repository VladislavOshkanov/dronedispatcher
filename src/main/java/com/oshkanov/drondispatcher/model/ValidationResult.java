package com.oshkanov.drondispatcher.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {
    boolean isValid = true;

    List<String> errorMessages = new ArrayList<>();
}
