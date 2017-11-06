package kryptonytt.service;

import kryptonytt.domain.Asset;
import kryptonytt.domain.KryptonyttUser;
import kryptonytt.domain.Portfolio;
import kryptonytt.entity.AssetHib;
import kryptonytt.entity.KryptonyttUserHib;
import kryptonytt.entity.PortfolioHib;
import kryptonytt.exception.PortfolioExists;
import kryptonytt.exception.PortfolioNotFound;
import kryptonytt.repository.AssetRepository;
import kryptonytt.repository.PortfolioRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final AssetRepository assetRepository;
    private final UserService userService;

    public PortfolioService(PortfolioRepository portfolioRepository, UserService userService, AssetRepository assetRepository) {
        this.portfolioRepository = portfolioRepository;
        this.assetRepository = assetRepository;
        this.userService = userService;
    }

    @Transactional
    public Portfolio refreshPortfolio(String portfolioName, Portfolio portfolio) throws ParseException {

        final Portfolio existingPortfolio = findPortfolio(portfolioName, portfolio.getUser());

        if (existingPortfolio == null) {
            throw new PortfolioNotFound(portfolioName, portfolio.getUser().getUsername());
        }

        if (!portfolioName.equals(portfolio.getName())) {
            final Portfolio portfolioWithNewName = findPortfolio(portfolio.getName(), portfolio.getUser());
            if (portfolioWithNewName != null) {
                throw new PortfolioExists(portfolioWithNewName.getName(), portfolio.getUser().getUsername());
            }
        }

        for (Asset ass : existingPortfolio.getAssets()) {
            AssetHib deleteCondition = new AssetHib(ass.getId());
            deleteCondition.setId(ass.getId());
            deleteCondition.setPortfolio(new PortfolioHib(existingPortfolio));
            assetRepository.delete(deleteCondition);
        }

        PortfolioHib portfolioHib = new PortfolioHib(portfolio);
        portfolioHib.setId(existingPortfolio.getId());
        portfolioHib.setUser(new KryptonyttUserHib(portfolio.getUser()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = sdf.parse(existingPortfolio.getCreated());
        portfolioHib.setCreated(date);

        Collection<AssetHib> assetHibs = new ArrayList<>();
        for (Asset ass : portfolio.getAssets()) {
            AssetHib assetHib = new AssetHib(ass);
            assetHib.setPortfolio(portfolioHib);
            assetHibs.add(assetHib);
        }
        portfolioHib.setAssets(assetHibs);

        portfolioRepository.save(portfolioHib);
        return findPortfolio(portfolioHib.getName(), portfolio.getUser());
    }

    @Transactional
    public void deletePortfolio(String portfolioName, KryptonyttUser user) {
        final Portfolio existingPortfolio = findPortfolio(portfolioName, user);

        if (existingPortfolio == null) {
            throw new PortfolioNotFound(portfolioName, user.getUsername());
        }

        PortfolioHib portfolioExample = new PortfolioHib(existingPortfolio);
        portfolioRepository.delete(portfolioExample);

    }

    @Transactional
    public Portfolio createPortfolio(String portfolioName, KryptonyttUser user, boolean isPublic) {
        KryptonyttUserHib userExample = new KryptonyttUserHib(user);
        PortfolioHib portfolioHib = new PortfolioHib();
        portfolioHib.setName(portfolioName);
        portfolioHib.setUser(userExample);
        portfolioHib.setPublic(isPublic);
        portfolioHib = portfolioRepository.save(portfolioHib);
        return PortfolioHib.toPortfolio(portfolioHib);
    }

    public Portfolio findPortfolio(String name, KryptonyttUser user) {
        KryptonyttUserHib userExample = new KryptonyttUserHib();
        userExample.setId(user.getId());
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
