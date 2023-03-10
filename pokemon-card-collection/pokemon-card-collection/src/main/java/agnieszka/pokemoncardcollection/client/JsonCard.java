package agnieszka.pokemoncardcollection.client;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JsonCard {

    private String id;
    private String name;
    private Image images;
}
