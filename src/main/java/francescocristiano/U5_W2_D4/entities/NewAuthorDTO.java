package francescocristiano.U5_W2_D4.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record NewAuthorDTO(
        @NotEmpty(message = "name cannot be empty")
        @Size(min = 3, max = 30, message = "name must be between 3 and 30 characters")
        String name,
        @NotEmpty(message = "surname cannot be empty")
        @Size(min = 3, max = 30, message = "surname must be between 3 and 30 characters")
        String surname,
        @NotEmpty(message = "email cannot be empty")
        @Email
        String email,
        @NotNull(message = "birthDate cannot be null")
        Date birthDate) {
}
