package francescocristiano.U5_W2_D4.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
        @NotEmpty(message = "birthDate cannot be empty")
        @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "birthDate must be in the format dd-mm-yyyy")
        String birthDate) {
}
