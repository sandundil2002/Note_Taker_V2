package lk.ijse.note_taker_v2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/welcome")
public class WelcomeController {

    @GetMapping
    public String welcome(Model model) {
        model.addAttribute("message", "Welcome to Note Taker V2 API");
        return "welcome";
    }

}
