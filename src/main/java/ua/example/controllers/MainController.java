package ua.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.example.dao.UserDAO;
import ua.example.models.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    //private List<User> users = new ArrayList<>();

    private final UserDAO userDAO;

    public MainController(UserDAO userDAO) {
        this.userDAO = userDAO;
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
        if (result.hasErrors()) {
            return "sign_up";
        }
        //users.add(user);
        return "redirect:/users";
    }

}
