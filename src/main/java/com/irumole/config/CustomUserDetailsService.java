package com.irumole.config;

import com.irumole.model.User;
import com.irumole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBuilder userBuilder;
        Optional<User> user = userRepository.getUser(username);
        if(!user.isPresent()){
            throw new UsernameNotFoundException(username);
        }
        userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
        userBuilder.password(user.get().getPassword());
        userBuilder.roles(user.get().getRoles());
        return userBuilder.build();
    }
}
