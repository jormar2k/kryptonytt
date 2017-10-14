package kryptonytt.service;

import kryptonytt.entity.KryptonyttUser;
import kryptonytt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createNewUser(String username) {
        userRepository.save(new KryptonyttUser(username));
    }
}
