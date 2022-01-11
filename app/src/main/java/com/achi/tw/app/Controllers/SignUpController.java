package com.achi.tw.app.Controllers;

import com.achi.tw.app.Dao.UserDao;
import com.achi.tw.app.Entity.User;
import com.achi.tw.app.Models.NewUser;
import com.achi.tw.app.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.achi.tw.app.Entity.Role;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SignUpController
{
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signUp")
    public ModelAndView signUp(Model model)
    {
        model.addAttribute("newUser", new NewUser());

        return new ModelAndView("signUp");
    }

    @PostMapping("/submit")
    public ModelAndView submit(@ModelAttribute NewUser newUser)
    {
        ModelAndView mav = new ModelAndView("redirect:/signUp");

        if (newUser.getPassword().equals(newUser.getConfirmedPassword())){
            User user = new User();
            user.setUsername(newUser.getUsername());
            user.setPassword(passwordEncoder.encode(newUser.getConfirmedPassword()));
            user.setDisabled(false);
            user.setRole("USER");

            //UserDao.addUser(user);
            userRepository.save(user);
            
            mav.setViewName("redirect:/login");
        }

        return mav;
    }
}
