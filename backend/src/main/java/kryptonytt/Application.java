package kryptonytt;

import kryptonytt.domain.KryptonyttUser;
import kryptonytt.domain.Portfolio;
import kryptonytt.service.AssetService;
import kryptonytt.service.PortfolioService;
import kryptonytt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;

@SpringBootApplication
@EntityScan("kryptonytt.entity")
public class Application {

    @Autowired
    UserService userService;
    @Autowired
    PortfolioService portfolioService;
    @Autowired
    AssetService assetService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {

        return args -> {
            KryptonyttUser user = new KryptonyttUser();
            user.setUsername("tobbe");
            user.setPassword("123");
            KryptonyttUser tobbe = userService.createUser(user);

            user = new KryptonyttUser();
            user.setUsername("jøgge");
            user.setPassword("123");
            KryptonyttUser jøgge = userService.createUser(user);

            Portfolio winfolio = portfolioService.createPortfolio("winfolio", tobbe.getId(), true);
            assetService.createAsset("IO", winfolio, new BigDecimal(54.321));
            assetService.createAsset("BTC", winfolio, new BigDecimal(25.83));

            Portfolio cumfolio = portfolioService.createPortfolio("cumfolio", tobbe.getId(), false);
            assetService.createAsset("HUBI", cumfolio, new BigDecimal(2899.83));

            Portfolio søplefolio = portfolioService.createPortfolio("søplefolio", jøgge.getId(), false);
            assetService.createAsset("ETH", søplefolio, new BigDecimal(54.321));
            assetService.createAsset("BTC", søplefolio, new BigDecimal(25.83));

        };
    }

}