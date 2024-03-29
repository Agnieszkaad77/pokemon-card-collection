package agnieszka.pokemoncardcollection.client;

import agnieszka.pokemoncardcollection.entity.CardEntity;
import agnieszka.pokemoncardcollection.repository.CardRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@AllArgsConstructor
public class PokemonTcgClient {

    private static final String URL = "https://api.pokemontcg.io/v2/cards";
    private CardRepository cardRepository;


    @PostConstruct
    private void download() {
        if (cardRepository.count() > 0) {
            return;
        }
        Thread threadDownloader = new Thread(() -> {
            int numberOfCards = 15851;
            int pageSize = 250;
            for (int i = 1; i <= numberOfCards / pageSize + 1; i++) {
                int currentPage = i;
                Thread threadOnePageDownloader = new Thread(() -> downloadPage(currentPage));
                threadOnePageDownloader.start();
            }
        });
        threadDownloader.start();
    }


    private void downloadPage(int page) {
        RestTemplate restTemplate = new RestTemplate();
        Cards cards = restTemplate.getForObject(URL + "?page=" + page, Cards.class);

        List<CardEntity> cardEntities = prepareCardEntities(cards);

        cardRepository.saveAll(cardEntities);
    }

    private List<CardEntity> prepareCardEntities(Cards cards) {
        List<CardEntity> cardEntities = cards.getData().stream()
                .map(jsonCard -> new CardEntity(jsonCard.getId(), jsonCard.getName(), jsonCard.getImages().getLarge()))
                .toList();
        return cardEntities;
    }
}
