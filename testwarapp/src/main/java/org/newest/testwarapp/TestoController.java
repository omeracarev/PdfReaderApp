package org.newest.testwarapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestoController {
    @GetMapping("index")
    public String homePage(Model model){
        model.addAttribute("pageTitle", "Ana Sayfa");
        return "index";
    }
}
