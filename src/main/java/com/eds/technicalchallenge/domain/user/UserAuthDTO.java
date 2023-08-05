package com.eds.technicalchallenge.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record UserAuthDTO(@NotBlank String username, @NotBlank String password){}
