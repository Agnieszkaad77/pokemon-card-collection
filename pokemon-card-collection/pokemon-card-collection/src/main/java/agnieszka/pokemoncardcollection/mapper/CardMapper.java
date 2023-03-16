package agnieszka.pokemoncardcollection.mapper;

import agnieszka.pokemoncardcollection.dto.CardDto;
import agnieszka.pokemoncardcollection.entity.CardEntity;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardEntity toCardEntity(CardDto cardDto) {
        return CardEntity.builder()
                .id(cardDto.getId())
                .name(cardDto.getName())
                .largeImage(cardDto.getLargeImage())
                .build();
    }

    public CardDto toCardDto(CardEntity cardEntity) {
        return CardDto.builder()
                .id(cardEntity.getId())
                .name(cardEntity.getName())
                .largeImage(cardEntity.getLargeImage())
                .build();
    }
}
