package agnieszka.pokemoncardcollection.repository;

import agnieszka.pokemoncardcollection.entity.AuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity, String> {

}
