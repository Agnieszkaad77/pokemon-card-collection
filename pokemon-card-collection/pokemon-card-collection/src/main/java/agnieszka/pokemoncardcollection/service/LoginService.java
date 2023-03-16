package agnieszka.pokemoncardcollection.service;

import agnieszka.pokemoncardcollection.dto.SessionDto;
import agnieszka.pokemoncardcollection.dto.UserDto;
import agnieszka.pokemoncardcollection.dto.UserLoginDto;
import agnieszka.pokemoncardcollection.entity.UserEntity;
import agnieszka.pokemoncardcollection.exception.LoginException;
import agnieszka.pokemoncardcollection.mapper.UserMapper;
import agnieszka.pokemoncardcollection.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    @Resource(name = "sessionScopedBean")
    private SessionDto sessionDto;
    private UserRepository userRepository;
    private UserMapper userMapper;


    public void login(UserLoginDto userLoginDto) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userLoginDto.getEmail());
        UserEntity userEntity = userOptional.orElseThrow(
                () -> new LoginException("Password or email is not correct"));
        if (!userEntity.getPassword().equals(userLoginDto.getPassword())) {
            throw new LoginException("Password or email is not correct");
        }
        sessionDto.login(userEntity);
    }

    public UserDto getLoggedUserDto() {
        if (sessionDto.getUser() == null) {
            throw new LoginException("User is not logged!");
        }
        return userMapper.toUserDto(sessionDto.getUser());
    }

    UserEntity getLoggedUserEntity() {
        if (sessionDto.getUser() == null) {
            throw new LoginException("User is not logged!");
        }
        return sessionDto.getUser();
    }
}
