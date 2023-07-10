package me.yuntodo.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank
    @Length(max = 20)
    private final String username;
    @NotBlank
    private final String password;
    @NotBlank
    private final String confirmPassword;
}
