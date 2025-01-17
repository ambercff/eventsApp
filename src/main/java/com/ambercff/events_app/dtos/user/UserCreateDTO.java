package com.ambercff.events_app.dtos.user;

import com.ambercff.events_app.models.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateDTO(@NotBlank String nome,
                            @NotBlank String email,
                            @NotBlank String senha,
                            @NotNull UserRole userRole
                            ) {
}
