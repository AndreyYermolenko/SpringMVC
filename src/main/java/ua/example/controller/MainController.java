package ua.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/hello")
    public String viewHello(@RequestParam(value = "name", required = false, defaultValue = "stranger") String name,
                            Model model) {
        model.addAttribute("msg", "Hello, " + name + "!");
        return "hello";
    }

    @GetMapping("/bye")
    public String viewBye(@RequestParam(value = "name", required = false, defaultValue = "stranger") String name,
                          Model model) {
        model.addAttribute("msg", "Bye, " + name + "!");
        return "bye";
    }

}
