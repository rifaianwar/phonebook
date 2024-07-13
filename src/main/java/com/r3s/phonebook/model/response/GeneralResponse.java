package com.r3s.phonebook.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse {
    private String statusCode;
    private String message;
    private Object data;
}
