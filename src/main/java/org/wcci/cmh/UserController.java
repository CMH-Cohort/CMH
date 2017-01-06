package org.wcci.cmh;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserRepository myUserRepository;

    @RequestMapping("/allUsers")
    public String displayEntireListOfTerms(Model model) {

        Iterable<User> users = myUserRepository.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @RequestMapping("/searchUser")
    public String search(@RequestParam(value = "username") String username, Model model) {
        Iterable<User> searchResults = myUserRepository.findByUsernameIgnoreCaseLike("%" + username + "%");
        model.addAttribute("users", searchResults);
        return "user-list";
    }

    @RequestMapping("/addUser")
    public String add(@RequestParam(value = "username") String username, Model model) {
        User user = myUserRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            new User(username);
            myUserRepository.save(user);
        }
        return displayEntireListOfTerms(model);
    }

    @RequestMapping("/removeUser")
    public String remove(@RequestParam(value = "username") String username, Model model) {
        User user = myUserRepository.findByUsernameIgnoreCase(username);
        myUserRepository.delete(user);
        return displayEntireListOfTerms(model);
    }


}