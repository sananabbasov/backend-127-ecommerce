package az.edu.itbrains.ecommerce.security;

import az.edu.itbrains.ecommerce.models.User;
import az.edu.itbrains.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;


@Configuration
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user != null){
            org.springframework.security.core.userdetails.User loggedUser = new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
            );

            return loggedUser;
        }

        throw new UsernameNotFoundException("User is not found");
    }
}
