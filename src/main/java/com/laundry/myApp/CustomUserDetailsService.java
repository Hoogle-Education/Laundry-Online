
package com.laundry.myApp;

import com.laundry.myApp.models.User;
import
        com.laundry.myApp.repository.UserRepository;

import java.util.stream.Collectors;

public class CustomUserDetailsService /*implements UserDetailsService*/ {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Override
//    public UserDetails loadUserByUsername(String usernameOrEmail)
//            throws UsernameNotFoundException {
//
//        User user = userRepository.findByUsername(usernameOrEmail);
//        if (user != null) {
//            return new org.springframework.security.core.userdetails.User(((User)
//                    user).getEmail(), user.getPassword(), user.getRoles().stream().map((role)
//                            -> new SimpleGrantedAuthority(role.getRole()))
//                    .collect(Collectors.toList()));
//        } else {
//            throw new
//                    UsernameNotFoundException("Invalid email or password");
//        }
//    }
}
 