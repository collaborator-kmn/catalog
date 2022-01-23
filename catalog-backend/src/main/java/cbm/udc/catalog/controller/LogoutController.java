package cbm.udc.catalog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Nickolay Burdiladze
 */
@Controller
public class LogoutController {

    @GetMapping("/logout")
    public RedirectView logout() {
        return new RedirectView("/");
    }
}