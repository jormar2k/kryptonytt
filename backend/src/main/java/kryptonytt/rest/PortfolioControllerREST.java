package kryptonytt.rest;

import io.jsonwebtoken.Jwts;
import kryptonytt.domain.KryptonyttUser;
import kryptonytt.domain.Portfolio;
import kryptonytt.exception.PortfolioExists;
import kryptonytt.security.SecurityConstants;
import kryptonytt.service.PortfolioService;
import kryptonytt.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collection;

@RestController
@RequestMapping("/portfolios")
public class PortfolioControllerREST {

    private final PortfolioService portfolioService;
    private final UserService userService;
    private final Environment environment;

    public PortfolioControllerREST(PortfolioService portfolioService,
                                   UserService userService,
                                   Environment environment) {

        this.portfolioService = portfolioService;
        this.userService = userService;
        this.environment = environment;
    }

    @GetMapping
    public Collection<Portfolio> findPortfolios(@RequestHeader(value="Authorization") String token) {
        KryptonyttUser user = getKryptonyttUserFromToken(token);
        return portfolioService.findPortfolios(user.getUsername());
    }

    @GetMapping(value ="/{username}")
    public Collection<Portfolio> findPublicPortfolios(@PathVariable String username) {
        return portfolioService.findPublicPortfolios(username);
    }

    @DeleteMapping(value ="/{name}")
    public ResponseEntity deletePortfolio(@RequestHeader(value="Authorization") String token, @PathVariable String name) {
        KryptonyttUser user = getKryptonyttUserFromToken(token);
        portfolioService.deletePortfolio(name, user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createPortfolio(@RequestHeader(value="Authorization") String token, @RequestBody Portfolio portfolio) {
        KryptonyttUser user = getKryptonyttUserFromToken(token);

        Portfolio existingPortfolio = portfolioService.findPortfolio(portfolio.getName(), user);
        if (existingPortfolio != null) {
            throw new PortfolioExists(existingPortfolio.getName(), user.getUsername());
        }

        Portfolio createdPortfolio = portfolioService.createPortfolio(portfolio.getName(), user, portfolio.isPublic());
        return new ResponseEntity<>(createdPortfolio, HttpStatus.CREATED);
    }

    @PutMapping(value ="/{name}")
    public ResponseEntity refreshPortfolios(@RequestHeader(value="Authorization") String token,
                                            @PathVariable String name,
                                            @RequestBody Portfolio portfolio) throws ParseException {

        KryptonyttUser user = getKryptonyttUserFromToken(token);
        portfolio.setUser(user);

        Portfolio refreshedPortfolio = portfolioService.refreshPortfolio(name, portfolio);
        return new ResponseEntity<>(refreshedPortfolio, HttpStatus.OK);
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