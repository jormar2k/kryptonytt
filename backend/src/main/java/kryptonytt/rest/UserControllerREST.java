package kryptonytt.rest;

import kryptonytt.entity.KryptonyttUser;
import kryptonytt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserControllerREST {

    @Autowired
    private UserService userService;

    @RequestMapping( method = RequestMethod.GET)
    public Collection<KryptonyttUser> allUsers() {
        return userService.findUsers();
    }

    @RequestMapping(value ="/{username}", method = RequestMethod.GET)
    public KryptonyttUser getUser(@PathVariable String username) {
        return userService.findUser(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Collection<KryptonyttUser> createUsers(@RequestBody Collection<KryptonyttUser> kryptonyttUsers) {
        return userService.createUsers(kryptonyttUsers);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteUsers(@RequestBody Collection<KryptonyttUser> kryptonyttUsers) {
        userService.deleteUsers(kryptonyttUsers);
    }

    @RequestMapping(value ="/{username}", method = RequestMethod.DELETE)
    public void deleteUsers(@PathVariable String username) {
        userService.deleteUser(username);
    }
}