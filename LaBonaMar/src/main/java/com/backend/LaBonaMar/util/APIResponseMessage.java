package com.backend.LaBonaMar.util;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseMessage {
    private String message;
    private HttpStatus status;
    private boolean success;
    private Object data;
}
