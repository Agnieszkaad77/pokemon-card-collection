package agnieszka.pokemoncardcollection.repository;

import agnieszka.pokemoncardcollection.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String> {

}
