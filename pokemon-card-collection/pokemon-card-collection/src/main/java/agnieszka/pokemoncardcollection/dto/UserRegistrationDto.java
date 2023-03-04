package agnieszka.pokemoncardcollection.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRegistrationDto {

    private String email;
    private String password;
    private String passwordRepeated;
    private boolean agree;
}
