package agnieszka.pokemoncardcollection.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private boolean agree;
    @Builder.Default
    private int pokeCoins = 20;
    private int points;
    //usunac points i zrobic metode na ilosc kart (size)
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private List<CardEntity> cards = new ArrayList<>();

    public void decreasePokeCoins(int price) {
        this.pokeCoins -= price;
    }

    public void addCards(List<CardEntity> purchasedCards) {
        this.cards.addAll(purchasedCards);
        this.points += purchasedCards.size();
    }
}
