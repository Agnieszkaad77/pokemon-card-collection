package agnieszka.pokemoncardcollection.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CardDto {

    private String id;
    private String name;
    private String largeImage;

}
