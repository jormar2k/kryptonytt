package kryptonytt.security;

import kryptonytt.entity.KryptonyttUserHib;
import kryptonytt.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KryptonyttUserHib kryptonyttUser = userRepository.findByUsername(username).orElse(null);
        if (kryptonyttUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(kryptonyttUser.getUsername(), kryptonyttUser.getPassword(), emptyList());
    }
}
