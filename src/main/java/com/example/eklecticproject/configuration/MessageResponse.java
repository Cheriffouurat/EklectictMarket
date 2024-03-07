package com.example.eklecticproject.configuration;
import lombok.Data;
import lombok.NonNull;
@Data
public class MessageResponse {
    @NonNull
    private String message;
}
