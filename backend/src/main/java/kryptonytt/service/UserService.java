package kryptonytt.service;

import kryptonytt.entity.KryptonyttUser;
import kryptonytt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;

@Component
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createNewUser(String username) {
        userRepository.save(new KryptonyttUser(username));
    }

    public Collection<KryptonyttUser> findUsers() {
        return userRepository.findAll();
    }

    public Collection<KryptonyttUser> createUsers(Collection<KryptonyttUser> kryptonyttUsers) {
        return userRepository.save(kryptonyttUsers);
    }

    public void deleteUsers(Collection<KryptonyttUser> kryptonyttUsers) {
        userRepository.delete(kryptonyttUsers);
    }

    public void deleteUser(String username) {
        userRepository.delete(new KryptonyttUser(username));
    }
}
