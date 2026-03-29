package com.latinhouse.backend.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class RefreshToken {
    private String username;
    private String refreshToken;
    private LocalDateTime expiryDate;
}
