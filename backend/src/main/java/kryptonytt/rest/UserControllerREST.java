package kryptonytt.rest;

import io.jsonwebtoken.Jwts;
import kryptonytt.domain.KryptonyttUser;
import kryptonytt.security.SecurityConstants;
import kryptonytt.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserControllerREST {

    private final UserService userService;
    private final Environment environment;

    public UserControllerREST(UserService userService, Environment environment) {
        this.userService = userService;
        this.environment = environment;
    }

    @PostMapping("/sign-up")
    public ResponseEntity createUser(@RequestBody KryptonyttUser kryptonyttUser) {
        userService.createUser(kryptonyttUser);
        return new ResponseEntity(HttpStatus.CREATED);
    }

//    @DeleteMapping(value ="/{username}")
//    public ResponseEntity deleteUser(@PathVariable String username) {
//        userService.deleteUser(username);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @PutMapping(value ="/settings")
    public ResponseEntity updateSettings(@RequestHeader(value="Authorization") String token, @RequestBody Map<String, Object> settings) {
        KryptonyttUser user = getKryptonyttUserFromToken(token);
        user.setSettings(settings);

        Map<String, Object> updatedSettings = userService.updateUserSettings(user).getSettings();

        return new ResponseEntity<>(updatedSettings, HttpStatus.OK);
    }

    @GetMapping(value ="/settings")
    public ResponseEntity getUserSettings(@RequestHeader(value="Authorization") String token) {
        KryptonyttUser user = getKryptonyttUserFromToken(token);
        return new ResponseEntity<>(user.getSettings(), HttpStatus.OK);
    }

    private KryptonyttUser getKryptonyttUserFromToken(@RequestHeader(value = "Authorization") String token) {
        String username = Jwts.parser()
                .setSigningKey(environment.getProperty("jwt.secret"))
                .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        return userService.findUser(username);
    }
}