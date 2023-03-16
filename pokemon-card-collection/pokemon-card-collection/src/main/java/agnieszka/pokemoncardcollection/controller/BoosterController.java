package agnieszka.pokemoncardcollection.controller;

import agnieszka.pokemoncardcollection.dto.CardDto;
import agnieszka.pokemoncardcollection.entity.UserEntity;
import agnieszka.pokemoncardcollection.exception.BoosterException;
import agnieszka.pokemoncardcollection.exception.LoginException;
import agnieszka.pokemoncardcollection.service.BoosterService;
import agnieszka.pokemoncardcollection.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoosterController {

    private LoginService loginService;
    private BoosterService boosterService;

    @GetMapping("/buy-booster")
    public String getBuyBoosterPage(Model model) {
        try {
            UserEntity userEntity = loginService.getLoggedUser();
            model.addAttribute("loggedUser", userEntity);
            addPokeCoinsAttribute(model);
        } catch (LoginException e) {
            return "redirect:/login";
        }
        return "booster";
    }

    @PostMapping("/buy-booster")
    public String processBuyBooster(Model model) {
        try {
            List<CardDto> cards = boosterService.buyBooster();
            model.addAttribute("cards", cards);
            addPokeCoinsAttribute(model);
        } catch (BoosterException e) {
            model.addAttribute("noPokeCoinsMessage", e.getMessage());
            return "booster";
        }
        return "booster";
    }

    private void addPokeCoinsAttribute(Model model) {
        model.addAttribute("pokeCoins", loginService.getLoggedUser().getPokeCoins());
    }
}
