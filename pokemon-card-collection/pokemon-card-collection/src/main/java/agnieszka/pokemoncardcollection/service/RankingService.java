package agnieszka.pokemoncardcollection.service;

import agnieszka.pokemoncardcollection.entity.UserEntity;
import agnieszka.pokemoncardcollection.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RankingService {

    private UserRepository userRepository;

    public List<UserEntity> getRanking() {
        return userRepository.findTop5ByAgreeTrueOrderByPointsDesc();
    }
}
