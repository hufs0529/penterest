package penterest.spring.domain.member.dto;

import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import penterest.spring.domain.member.entity.Authority;
import penterest.spring.domain.member.entity.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoginDto{
        @NotBlank(message = "이메일을 입력해주세요")
        @Email(message = "유효한 이메일 주소를 입력해주세요")
        String email;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
                message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
        String password;

}