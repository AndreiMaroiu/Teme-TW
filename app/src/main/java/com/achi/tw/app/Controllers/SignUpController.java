package com.achi.tw.app.Controllers;

import com.achi.tw.app.Entity.User;
import com.achi.tw.app.Entity.Role;
import com.achi.tw.app.Models.NewUser;
import com.achi.tw.app.Repositories.RoleRepository;
import com.achi.tw.app.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
public class SignUpController
{
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public SignUpController(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/signUp")
    public ModelAndView signUp(Model model)
    {
        model.addAttribute("newUser", new NewUser());
        model.addAttribute("roles", roleRepository.findAll());

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
            Role role = roleRepository.getRoleByName(newUser.getRole());
            var roles= new ArrayList<Role>();
            roles.add(role);
            user.setRoles(roles);

            userRepository.save(user);

            mav.setViewName("redirect:/login");
        }

        return mav;
    }
}
