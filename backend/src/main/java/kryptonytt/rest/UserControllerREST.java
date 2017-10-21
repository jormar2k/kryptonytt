package kryptonytt.rest;

import kryptonytt.domain.KryptonyttUser;
import kryptonytt.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserControllerREST {

    private final UserService userService;

    public UserControllerREST(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value ="/{username}")
    public KryptonyttUser getUser(@PathVariable String username) {
        return userService.findUser(username);
    }

    @PostMapping("/sign-up")
    public KryptonyttUser createUser(@RequestBody KryptonyttUser kryptonyttUser) {
        return userService.createUser(kryptonyttUser);
    }

    @DeleteMapping(value ="/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }
}