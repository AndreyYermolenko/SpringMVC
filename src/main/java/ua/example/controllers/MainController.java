package ua.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.example.dao.UserDAO;
import ua.example.models.User;
import ua.example.util.UserValidator;

import javax.validation.Valid;

@Controller
public class MainController {

    private final UserDAO userDAO;
    private final UserValidator userValidator;

    public MainController(UserDAO userDAO, UserValidator userValidator) {
        this.userDAO = userDAO;
        this.userValidator = userValidator;
    }

    @GetMapping("/")
    public String startPage(Model model) {
        model.addAttribute("users", userDAO.getAll());
        return "redirect:users";
    }

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
        model.addAttribute("users", userDAO.getAll());
        return "users";
    }

    @GetMapping("/users/new")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "sign_up";
        }
        userDAO.add(user);
        return "redirect:/users";
    }

}
