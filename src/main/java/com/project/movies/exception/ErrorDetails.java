package com.project.movies.exception;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
