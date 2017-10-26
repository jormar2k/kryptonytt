package kryptonytt;

import kryptonytt.domain.Asset;
import kryptonytt.domain.KryptonyttUser;
import kryptonytt.domain.Portfolio;
import kryptonytt.repository.BaseRepositoryImpl;
import kryptonytt.service.PortfolioService;
import kryptonytt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
//@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class, basePackages = {"kryptonytt.repository"})
@EnableTransactionManagement
@EntityScan("kryptonytt.entity")
public class Application {

    @Autowired
    UserService userService;
    @Autowired
    PortfolioService portfolioService;

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

            Portfolio winfolio = portfolioService.createPortfolio("winfolio", tobbe, true);
            Collection<Asset> assets = new ArrayList<>();
            assets.add(new Asset("IO", new BigDecimal(321)));
            assets.add(new Asset("BTC", new BigDecimal(321)));
            portfolioService.addAssetsToPortfolio(winfolio.getName(), tobbe, assets);

        };
    }

}