package ru.itis.memories.dto;

import lombok.Data;
import ru.itis.memories.models.User;
import ru.itis.memories.validation.ValidNames;
import ru.itis.memories.validation.ValidPassword;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@ValidNames(
        message = "{errors.invalid.names}",
        name = "firstName",
        surname = "lastName"
)
public class UserForm {

    @NotEmpty(message = "{errors.incorrect.email}")
    @Email(message = "{errors.incorrect.email}")
    private String email;


    @ValidPassword(message = "{errors.invalid.password}")
    private String password;


    @NotEmpty(message = "{errors.invalid.empty}")
    private String firstName;

    @NotEmpty(message = "{errors.invalid.empty}")
    private String lastName;

    private User.Role role = User.Role.USER;
}
