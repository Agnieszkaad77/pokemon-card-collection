package agnieszka.pokemoncardcollection.configuration;

import agnieszka.pokemoncardcollection.dto.SessionDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class SessionConfiguration {

    @Bean
    @SessionScope
    public SessionDto sessionScopedBean() {
        return new SessionDto();
    }
}
