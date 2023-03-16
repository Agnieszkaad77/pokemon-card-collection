package agnieszka.pokemoncardcollection.controller;

import agnieszka.pokemoncardcollection.dto.AuctionDto;
import agnieszka.pokemoncardcollection.dto.UserDto;
import agnieszka.pokemoncardcollection.exception.LoginException;
import agnieszka.pokemoncardcollection.service.AuctionService;
import agnieszka.pokemoncardcollection.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class CollectionController {

    private LoginService loginService;
    private AuctionService auctionService;

    @GetMapping("/collection")
    public String getCollectionPage(Model model) {
        try {
            UserDto userDto = loginService.getLoggedUserDto();
            model.addAttribute("loggedUser", userDto);
            AuctionDto auctionDto = new AuctionDto();
            model.addAttribute("auction", auctionDto);
        } catch (LoginException e) {
            return "redirect:/login";
        }
        return "collection";
    }

    @PostMapping("/collection/sell")
    public String createAuction(AuctionDto auctionDto) {
        auctionService.saveAuction(auctionDto);
        return "redirect:/collection";
    }
}
