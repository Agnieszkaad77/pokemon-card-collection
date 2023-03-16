package agnieszka.pokemoncardcollection.mapper;

import agnieszka.pokemoncardcollection.dto.AuctionDto;
import agnieszka.pokemoncardcollection.entity.AuctionEntity;
import org.springframework.stereotype.Component;

@Component
public class AuctionMapper {

    public AuctionEntity toAuctionEntity(AuctionDto auctionDto) {
        return AuctionEntity.builder()
                .cardId(auctionDto.getCardId())
                .price(auctionDto.getPrice())
                .amount(auctionDto.getAmount())
                .build();
    }

    public AuctionDto toAuctionDto(AuctionEntity auctionEntity) {
        return AuctionDto.builder()
                .cardId(auctionEntity.getCardId())
                .price(auctionEntity.getPrice())
                .amount(auctionEntity.getAmount())
                .build();
    }
}
