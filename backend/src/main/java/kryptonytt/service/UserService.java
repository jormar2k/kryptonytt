package kryptonytt.service;

import kryptonytt.domain.KryptonyttUser;
import kryptonytt.entity.KryptonyttUserHib;
import kryptonytt.exception.UserNotFound;
import kryptonytt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public KryptonyttUser createUser(KryptonyttUser kryptonyttUser) {
        KryptonyttUserHib kryptonyttUserHib = new KryptonyttUserHib();
        kryptonyttUserHib.setUsername(kryptonyttUser.getUsername());
        kryptonyttUserHib.setPassword(bCryptPasswordEncoder.encode(kryptonyttUser.getPassword()));
        KryptonyttUserHib saved = userRepository.save(kryptonyttUserHib);
        return KryptonyttUserHib.toKryptonyttUser(saved);
    }


    public void deleteUser(String username) {
        KryptonyttUserHib userExample = new KryptonyttUserHib();
        userExample.setUsername(username);
        userRepository.delete(userExample);
    }

    public KryptonyttUser findUser(String username) {
        KryptonyttUserHib kryptonyttUserHib = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFound(username));
        return KryptonyttUserHib.toKryptonyttUser(kryptonyttUserHib);
    }
}
