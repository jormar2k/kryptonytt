package kryptonytt.service;

import kryptonytt.domain.KryptonyttUser;
import kryptonytt.entity.KryptonyttUserHib;
import kryptonytt.exception.UserNotFound;
import kryptonytt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public KryptonyttUser createUser(KryptonyttUser kryptonyttUser) {
        if (kryptonyttUser.getSettings() == null) {
            Map<String, Object> defaultSettings = new HashMap<>();
            defaultSettings.put("defaultCurrency", "USD");
            defaultSettings.put("currencies", Arrays.asList("BTC"));
            defaultSettings.put("nightmode", true);
            kryptonyttUser.setSettings(defaultSettings);
        }

        KryptonyttUserHib kryptonyttUserHib = new KryptonyttUserHib(kryptonyttUser);
        kryptonyttUserHib.setPassword(bCryptPasswordEncoder.encode(kryptonyttUser.getPassword()));


        KryptonyttUserHib saved = userRepository.save(kryptonyttUserHib);
        return KryptonyttUserHib.toKryptonyttUser(saved);
    }

    @Transactional
    public KryptonyttUser updateUserSettings(KryptonyttUser kryptonyttUser) {
        KryptonyttUserHib kryptonyttUserHib = new KryptonyttUserHib(kryptonyttUser);
        kryptonyttUserHib.setId(kryptonyttUser.getId());
        KryptonyttUserHib saved = userRepository.save(kryptonyttUserHib);
        return KryptonyttUserHib.toKryptonyttUser(saved);
    }

    @Transactional
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
