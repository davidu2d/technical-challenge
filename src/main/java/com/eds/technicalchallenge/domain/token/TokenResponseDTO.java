package com.eds.technicalchallenge.domain.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record TokenResponseDTO(@JsonProperty("access_token")String token) {
}
