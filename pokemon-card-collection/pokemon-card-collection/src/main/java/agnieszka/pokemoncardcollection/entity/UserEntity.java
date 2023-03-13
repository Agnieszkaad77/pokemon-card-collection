package agnieszka.pokemoncardcollection.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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
    private int points;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Card> cards;

    public UserEntity(String email, String password, boolean agree) {
        this.email = email;
        this.password = password;
        this.agree = agree;
        this.pokeCoins = 20;
        this.cards = new ArrayList<>();
    }

    public void decreasePokeCoins(int price) {
        this.pokeCoins -= price;
    }

    public void addCards(List<Card> purchasedCards) {
        this.cards.addAll(purchasedCards);
        this.points += purchasedCards.size();
    }
}
