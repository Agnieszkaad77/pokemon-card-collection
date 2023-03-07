package agnieszka.pokemoncardcollection.dto;

import agnieszka.pokemoncardcollection.entity.UserEntity;

import java.time.LocalDateTime;

public class SessionDto {

    private UserEntity userEntity;
    private LocalDateTime loginDate;

    public void login(UserEntity userEntity) {
        this.userEntity = userEntity;
        this.loginDate = LocalDateTime.now();
    }

    public UserEntity getUser() {
        return userEntity;
    }
}
