package com.block16springcloud.block16springcloud.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

}
