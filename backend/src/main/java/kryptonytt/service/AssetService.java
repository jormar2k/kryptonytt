package kryptonytt.service;

import kryptonytt.domain.Portfolio;
import kryptonytt.entity.AssetHib;
import kryptonytt.entity.KryptonyttUserHib;
import kryptonytt.entity.PortfolioHib;
import kryptonytt.repository.AssetRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class AssetService {

    private final AssetRepository assetRepository;
    private final PortfolioService portfolioService;
    private UserService userService;

    public AssetService(AssetRepository assetRepository, PortfolioService portfolioService, UserService userService) {
        this.assetRepository = assetRepository;
        this.portfolioService = portfolioService;
        this.userService = userService;
    }

    public void createAsset(String name, Portfolio portfolio, BigDecimal amount) {
        Portfolio existingPortfolio = portfolioService.findPortfolio(portfolio.getName(), portfolio.getUser().getId());

        KryptonyttUserHib userExample = new KryptonyttUserHib();
        userExample.setId(existingPortfolio.getUser().getId());

        PortfolioHib portfolioExample = new PortfolioHib();
        portfolioExample.setUser(userExample);
        portfolioExample.setId(existingPortfolio.getId());

        assetRepository.save(new AssetHib(name, portfolioExample, amount));
    }

}
