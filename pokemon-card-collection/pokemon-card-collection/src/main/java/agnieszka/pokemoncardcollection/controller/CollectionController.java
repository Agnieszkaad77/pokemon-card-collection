package agnieszka.pokemoncardcollection.controller;

import agnieszka.pokemoncardcollection.entity.UserEntity;
import agnieszka.pokemoncardcollection.exception.LoginException;
import agnieszka.pokemoncardcollection.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CollectionController {

    private LoginService loginService;

    @GetMapping("/collection")
    public String getCollectionPage(Model model) {
        try {
            UserEntity userEntity = loginService.getLoggedUser();
            model.addAttribute("loggedUser", userEntity);
        } catch (LoginException e) {
            return "redirect:/login";
        }
        return "collection";
    }
}
