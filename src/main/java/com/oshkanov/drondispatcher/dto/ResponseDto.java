package com.oshkanov.drondispatcher.dto;

import java.util.ArrayList;
import java.util.List;

import com.oshkanov.drondispatcher.model.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto<T> {
    public ResponseDto(RequestStatus status, T response) {
        this.status = status;
        this.response = response;
    }

    public ResponseDto(RequestStatus status, List<String> errorMessages) {
        this.status = status;
        this.errorMessages = errorMessages;
    }

    private RequestStatus status;

    private T response;

    private List<String> errorMessages = new ArrayList<>();
}
