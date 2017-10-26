package kryptonytt.rest;

import kryptonytt.domain.KryptonyttUser;
import kryptonytt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createUser(@RequestBody KryptonyttUser kryptonyttUser) {
        userService.createUser(kryptonyttUser);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping(value ="/{username}")
    public ResponseEntity deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity(HttpStatus.OK);
    }
}