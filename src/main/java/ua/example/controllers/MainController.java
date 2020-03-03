package ua.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.example.models.User;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private List<User> users = new ArrayList<>();

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

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/new")
    public String getSignUp() {
        return "sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute User user) {
        users.add(user);
        return "redirect:/users";
    }

}
