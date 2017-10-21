package kryptonytt.rest;

import io.jsonwebtoken.Jwts;
import kryptonytt.domain.Asset;
import kryptonytt.domain.KryptonyttUser;
import kryptonytt.domain.Portfolio;
import kryptonytt.exception.PortfolioExists;
import kryptonytt.exception.PortfolioNotFound;
import kryptonytt.service.AssetService;
import kryptonytt.service.PortfolioService;
import kryptonytt.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class BaseControllerREST {

    private static final String TOKEN_PREFIX = "Bearer";

    private final PortfolioService portfolioService;
    private final UserService userService;
    private final Environment environment;
    private final AssetService assetService;

    public BaseControllerREST(PortfolioService portfolioService, UserService userService, Environment environment, AssetService assetService) {
        this.portfolioService = portfolioService;
        this.userService = userService;
        this.environment = environment;
        this.assetService = assetService;
    }

    @GetMapping(value ="/portfolios")
    public Collection<Portfolio> findPortfolios(@RequestHeader(value="Authorization") String token) {
        KryptonyttUser user = getKryptonyttUserFromToken(token);
        return portfolioService.findPortfolios(user.getUsername());
    }

    @PostMapping(value ="/portfolios")
    public ResponseEntity createPortfolio(@RequestHeader(value="Authorization") String token, @RequestBody Portfolio portfolio) {
        KryptonyttUser user = getKryptonyttUserFromToken(token);

        Portfolio existingPortfolio = portfolioService.findPortfolio(portfolio.getName(), user.getId());
        if (existingPortfolio != null) {
            throw new PortfolioExists(existingPortfolio.getName(), user.getUsername());
        }

        portfolioService.createPortfolio(portfolio.getName(), user.getId(), portfolio.isPublic());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping(value ="/portfolios/{name}/assets")
    public ResponseEntity addAssets(@RequestHeader(value="Authorization") String token, @PathVariable String name, @RequestBody Collection<Asset> assets) {
        KryptonyttUser user = getKryptonyttUserFromToken(token);

        Portfolio existingPortfolio = portfolioService.findPortfolio(name, user.getId());
        if (existingPortfolio == null) {
            throw new PortfolioNotFound(existingPortfolio.getName(), user.getUsername());
        }

        assets.forEach(asset -> assetService.createAsset(asset.getName(), existingPortfolio, asset.getAmount()));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    private KryptonyttUser getKryptonyttUserFromToken(@RequestHeader(value = "Authorization") String token) {
        String username = Jwts.parser()
                .setSigningKey(environment.getProperty("jwt.secret"))
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        return userService.findUser(username);
    }
}