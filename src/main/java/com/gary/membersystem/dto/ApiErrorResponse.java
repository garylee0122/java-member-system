package com.gary.membersystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {

    private boolean success;

    private String message;

    private Map<String, String> errors;
}