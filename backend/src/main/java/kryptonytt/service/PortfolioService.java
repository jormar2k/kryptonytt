package kryptonytt.service;

import kryptonytt.domain.Asset;
import kryptonytt.domain.KryptonyttUser;
import kryptonytt.domain.Portfolio;
import kryptonytt.entity.AssetHib;
import kryptonytt.entity.KryptonyttUserHib;
import kryptonytt.entity.PortfolioHib;
import kryptonytt.exception.PortfolioNotFound;
import kryptonytt.repository.AssetRepository;
import kryptonytt.repository.PortfolioRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final UserService userService;
    private final AssetRepository assetRepository;

    public PortfolioService(PortfolioRepository portfolioRepository, UserService userService, AssetRepository assetRepository) {
        this.portfolioRepository = portfolioRepository;
        this.userService = userService;
        this.assetRepository = assetRepository;
    }

    @Transactional
    public void addAssetsToPortfolio(String portfolioName, KryptonyttUser user, Collection<Asset> assets) {

        final Portfolio existingPortfolio = findPortfolio(portfolioName, user.getId());
        if (existingPortfolio == null) {
            throw new PortfolioNotFound(portfolioName, user.getUsername());
        }

        PortfolioHib portfolioExample = new PortfolioHib();
        portfolioExample.setId(existingPortfolio.getId());

        assets.forEach(asset -> assetRepository.saveAndFlush(new AssetHib(asset.getIdentifier(), portfolioExample, asset.getAmount())));

    }

    @Transactional
    public Portfolio createPortfolio(String portfolioName, KryptonyttUser user, boolean isPublic) {
        KryptonyttUserHib userExample = new KryptonyttUserHib();
        userExample.setId(user.getId());
        PortfolioHib portfolio = new PortfolioHib();
        portfolio.setName(portfolioName);
        portfolio.setUser(userExample);
        portfolio.setPublic(isPublic);
        portfolio = portfolioRepository.save(portfolio);
        return PortfolioHib.toPortfolio(portfolio);
    }

    public Portfolio findPortfolio(String name, Long userId) {
        KryptonyttUserHib userExample = new KryptonyttUserHib();
        userExample.setId(userId);
        PortfolioHib portFolioExample = new PortfolioHib();
        portFolioExample.setName(name);
        portFolioExample.setUser(userExample);
        PortfolioHib portfolioHib = portfolioRepository.findOne(Example.of(portFolioExample));

        if (portfolioHib == null) {
            return null;
        }
        return PortfolioHib.toPortfolio(portfolioHib);
    }

    public Collection<Portfolio> findPortfolios(String username) {
        KryptonyttUser existingUser = userService.findUser(username);

        KryptonyttUserHib userExample = new KryptonyttUserHib();
        userExample.setId(existingUser.getId());

        PortfolioHib portfolioExample = new PortfolioHib();
        portfolioExample.setUser(userExample);
        List<PortfolioHib> portfolioHibs = portfolioRepository.findAll(Example.of(portfolioExample));

        List<Portfolio> result = new ArrayList<>();
        for (PortfolioHib portfolioHib : portfolioHibs) {
            result.add(PortfolioHib.toPortfolio(portfolioHib));
        }

        return result;
    }
}
