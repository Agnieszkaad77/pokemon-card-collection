package agnieszka.pokemoncardcollection.service;

import agnieszka.pokemoncardcollection.dto.UserRegistrationDto;
import agnieszka.pokemoncardcollection.entity.UserEntity;
import agnieszka.pokemoncardcollection.exception.RegistrationException;
import agnieszka.pokemoncardcollection.mapper.UserMapper;
import agnieszka.pokemoncardcollection.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public void register(UserRegistrationDto userRegistrationDto) {
        if (checkMandatoryFields(userRegistrationDto)) {
            throw new IllegalArgumentException("Mandatory fields are not filled in!");
        }
        if (userRepository.existsByEmail(userRegistrationDto.getEmail())
                || !checkPasswordCorrectness(userRegistrationDto)) {
            throw new RegistrationException("Email or password is not correct!");
        }
        UserEntity userEntity = userMapper.toUserEntity(userRegistrationDto);
        userRepository.save(userEntity);
    }

    private boolean checkMandatoryFields(UserRegistrationDto userRegistrationDto) {
        return userRegistrationDto.getEmail().isBlank()
                || userRegistrationDto.getPassword().isBlank()
                || userRegistrationDto.getPasswordRepeated().isBlank();
    }

    private boolean checkPasswordCorrectness(UserRegistrationDto userRegistrationDto) {
        return userRegistrationDto.getPassword().equals(userRegistrationDto.getPasswordRepeated());
    }
}
