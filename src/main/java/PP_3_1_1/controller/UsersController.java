package PP_3_1_1.controller;


import PP_3_1_1.model.User;
import PP_3_1_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        User userById = userService.getUserById(id);
        if (userById != null) {
            model.addAttribute("user", userById);
            return "show";
        } else {
            return "not-found-user";
        }
    }

    @GetMapping("/new")
    public String getUserFormForCreate(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        userService.saveUser(user);
        return "redirect:/users";

    }

    @GetMapping("/{id}/update")
    public String getUserFromForUpdate(@PathVariable("id") int id, Model model) {
        User userById = userService.getUserById(id);
        if (userById != null) {
            model.addAttribute("user", userService.getUserById(id));
            return "edit";
        } else {
            return "not-found-user";
        }
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}