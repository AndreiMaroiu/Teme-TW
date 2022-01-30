package com.tw.app.services;

import com.tw.app.dto.NewUser;
import com.tw.app.entities.Role;
import com.tw.app.entities.User;
import com.tw.app.repositoties.RoleRepository;
import com.tw.app.repositoties.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SignUpService
{
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Iterable<Role> findAll()
    {
        return roleRepository.findAll();
    }

    public boolean save(NewUser newUser)
    {
        try
        {
            if (newUser.getPassword().equals(newUser.getConfirmedPassword())){
                User user = new User();
                user.setUsername(newUser.getUsername());
                user.setPassword(passwordEncoder.encode(newUser.getConfirmedPassword()));
                user.setDisabled(false);
                Role role = roleRepository.getRoleByName(newUser.getRole());
                var roles = new ArrayList<Role>();
                roles.add(role);
                user.setRoles(roles);

                userRepository.save(user);

                return true;
            }

            return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
