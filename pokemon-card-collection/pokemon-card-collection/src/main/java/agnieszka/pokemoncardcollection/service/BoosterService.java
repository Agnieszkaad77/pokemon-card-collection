package agnieszka.pokemoncardcollection.service;

import agnieszka.pokemoncardcollection.dto.CardDto;
import agnieszka.pokemoncardcollection.entity.CardEntity;
import agnieszka.pokemoncardcollection.entity.UserEntity;
import agnieszka.pokemoncardcollection.exception.BoosterException;
import agnieszka.pokemoncardcollection.mapper.CardMapper;
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
    private CardMapper cardMapper;

    public List<CardDto> buyBooster() {
        if (!verifyBalance()) {
            throw new BoosterException("You have not enough Poke Coins!");
        }
        List<CardEntity> randomCards = prepareBooster();
        processPurchase(randomCards);

        return randomCards.stream()
                .map(cardEntity -> cardMapper.toCardDto(cardEntity))
                .toList();
    }

    private boolean verifyBalance() {
        return loginService.getLoggedUser().getPokeCoins() >= PRICE;
    }

    private List<CardEntity> prepareBooster() {
        List<CardEntity> cards = cardRepository.findAll();
        Random random = new Random();
        List<CardEntity> randomCards = new ArrayList<>();
        for (int i = 0; i < BOOSTER_SIZE; i++) {
            CardEntity card = cards.get(random.nextInt(cards.size()));
            if (randomCards.contains(card)) {
                i--;
            } else {
                randomCards.add(card);
            }
        }
        return randomCards;
    }

    private void processPurchase(List<CardEntity> randomCards) {
        UserEntity loggedUser = loginService.getLoggedUser();
        loggedUser.decreasePokeCoins(PRICE);
        loggedUser.addCards(randomCards);
        userRepository.save(loggedUser);
    }
}
