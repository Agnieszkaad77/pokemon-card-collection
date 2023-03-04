package agnieszka.pokemoncardcollection.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private boolean agree;
    private int pokeCoins;

    public UserEntity(String email, String password, boolean agree) {
        this.email = email;
        this.password = password;
        this.agree = agree;
        pokeCoins = 20;
    }
}
