package agnieszka.pokemoncardcollection.mapper;

import agnieszka.pokemoncardcollection.dto.UserDto;
import agnieszka.pokemoncardcollection.dto.UserLoginDto;
import agnieszka.pokemoncardcollection.dto.UserRegistrationDto;
import agnieszka.pokemoncardcollection.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private CardMapper cardMapper;

    public UserLoginDto toUserLoginDto(UserEntity userEntity) {
        return UserLoginDto.builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }

    public UserEntity toUserEntity(UserRegistrationDto userRegistrationDto) {
        return UserEntity.builder()
                .email(userRegistrationDto.getEmail())
                .password(userRegistrationDto.getPassword())
                .agree(userRegistrationDto.isAgree())
                .build();
    }

    public UserDto toUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .pokeCoins(userEntity.getPokeCoins())
                .points(userEntity.getPoints())
                .cards((userEntity.getCards().stream().map(cardEntity -> cardMapper.toCardDto(cardEntity)).collect(Collectors.toList())))
                .build();
    }
}
