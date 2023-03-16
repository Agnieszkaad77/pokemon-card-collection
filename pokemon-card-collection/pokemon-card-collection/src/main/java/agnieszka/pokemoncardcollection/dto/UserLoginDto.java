package agnieszka.pokemoncardcollection.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    private String email;
    private String password;

}
