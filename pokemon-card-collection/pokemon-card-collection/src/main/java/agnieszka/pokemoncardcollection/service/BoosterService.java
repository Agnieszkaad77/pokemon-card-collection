package agnieszka.pokemoncardcollection.service;

import agnieszka.pokemoncardcollection.entity.Card;
import agnieszka.pokemoncardcollection.entity.UserEntity;
import agnieszka.pokemoncardcollection.exception.BoosterException;
import agnieszka.pokemoncardcollection.repository.CardRepository;
import agnieszka.pokemoncardcollection.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class BoosterService {

    private static final int PRICE = 5;
    private static final int BOOSTER_SIZE = 5;

    private CardRepository cardRepository;
    private LoginService loginService;
    private UserRepository userRepository;

    public List<Card> buyBooster() {
        if (!verifyBalance()) {
            throw new BoosterException("You have not enough Poke Coins!");
        }
        List<Card> randomCards = prepareBooster();
        processPurchase(randomCards);

        return randomCards;
    }

    private boolean verifyBalance() {
        return loginService.getLoggedUser().getPokeCoins() > PRICE;
    }

    private List<Card> prepareBooster() {
        List<Card> cards = cardRepository.findAll();
        Random random = new Random();
        List<Card> randomCards = new ArrayList<>();
        for (int i = 0; i < BOOSTER_SIZE; i++) {
            Card card = cards.get(random.nextInt(cards.size()));
            if (randomCards.contains(card)) {
                i--;
            } else {
                randomCards.add(card);
            }
        }
        return randomCards;
    }

    private void processPurchase(List<Card> randomCards) {
        UserEntity loggedUser = loginService.getLoggedUser();
        loggedUser.decreasePokeCoins(PRICE);
        loggedUser.addCards(randomCards);
        userRepository.save(loggedUser);
    }
}
