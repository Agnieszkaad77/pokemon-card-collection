package agnieszka.pokemoncardcollection.service;

import agnieszka.pokemoncardcollection.dto.AuctionDto;
import agnieszka.pokemoncardcollection.entity.AuctionEntity;
import agnieszka.pokemoncardcollection.mapper.AuctionMapper;
import agnieszka.pokemoncardcollection.repository.AuctionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuctionService {

    private AuctionRepository auctionRepository;
    private AuctionMapper auctionMapper;

    public void saveAuction(AuctionDto auctionDto) {
        AuctionEntity auctionEntity = auctionMapper.toAuctionEntity(auctionDto);
        auctionRepository.save(auctionEntity);
    }
}
