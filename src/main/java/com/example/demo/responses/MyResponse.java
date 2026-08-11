package com.example.demo.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode

@Data
@AllArgsConstructor
public class MyResponse {
    @JsonIgnore
    private long id;
    @JsonProperty("response_message")
    private String message;
    private int status;
}