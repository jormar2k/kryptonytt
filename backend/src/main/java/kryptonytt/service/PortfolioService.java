package kryptonytt.service;

import kryptonytt.domain.*;
import kryptonytt.entity.*;
import kryptonytt.exception.PortfolioExists;
import kryptonytt.exception.PortfolioNotFound;
import kryptonytt.repository.CoinRepository;
import kryptonytt.repository.CustomAssetRepository;
import kryptonytt.repository.FiatRepository;
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
    private final CoinRepository coinRepository;
    private final CustomAssetRepository customAssetRepository;
    private final FiatRepository fiatRepository;
    private final UserService userService;

    public PortfolioService(PortfolioRepository portfolioRepository,
                            UserService userService,
                            CoinRepository coinRepository,
                            CustomAssetRepository customAssetRepository,
                            FiatRepository fiatRepository) {

        this.portfolioRepository = portfolioRepository;
        this.coinRepository = coinRepository;
        this.userService = userService;
        this.customAssetRepository = customAssetRepository;
        this.fiatRepository = fiatRepository;
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

        deleteCoinsOnPortfolio(existingPortfolio);
        deleteCustomAssetsOnPortfolio(existingPortfolio);
        deleteFiatsOnPortfolio(existingPortfolio);

        PortfolioHib portfolioHib = new PortfolioHib(portfolio);
        portfolioHib.setId(existingPortfolio.getId());
        portfolioHib.setUser(new KryptonyttUserHib(portfolio.getUser()));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = sdf.parse(existingPortfolio.getCreated());
        portfolioHib.setCreated(date);

        addAssetsToPortfolio(portfolio, portfolioHib);

        portfolioRepository.save(portfolioHib);
        return findPortfolio(portfolioHib.getName(), portfolio.getUser());
    }

    private void addAssetsToPortfolio(Portfolio portfolio, PortfolioHib portfolioHib) {
        Collection<CoinHib> coinHibs = new ArrayList<>();
        for (Coin ass : portfolio.getCoins()) {
            CoinHib coinHib = new CoinHib(ass);
            coinHib.setPortfolio(portfolioHib);
            coinHibs.add(coinHib);
        }
        portfolioHib.setCoinHibs(coinHibs);

        Collection<CustomAssetHib> customAssetHibs = new ArrayList<>();
        for (CustomAsset custAss : portfolio.getCustomAssets()) {
            CustomAssetHib customAssetHib = new CustomAssetHib(custAss);
            customAssetHib.setPortfolio(portfolioHib);
            customAssetHibs.add(customAssetHib);
        }
        portfolioHib.setCustomAssetHibs(customAssetHibs);

        Collection<FiatHib> fiatHibs = new ArrayList<>();
        for (Fiat fiat : portfolio.getFiat()) {
            FiatHib fiatHib = new FiatHib(fiat);
            fiatHib.setPortfolio(portfolioHib);
            fiatHibs.add(fiatHib);
        }
        portfolioHib.setFiatHibs(fiatHibs);
    }

    private void deleteFiatsOnPortfolio(Portfolio existingPortfolio) {
        for (Fiat fiat : existingPortfolio.getFiat()) {
            FiatHib deleteCondition = new FiatHib(fiat.getId());
            deleteCondition.setPortfolio(new PortfolioHib(existingPortfolio));
            fiatRepository.delete(deleteCondition);
        }
    }

    private void deleteCustomAssetsOnPortfolio(Portfolio existingPortfolio) {
        for (CustomAsset ass : existingPortfolio.getCustomAssets()) {
            CustomAssetHib deleteCondition = new CustomAssetHib(ass.getId());
            deleteCondition.setPortfolio(new PortfolioHib(existingPortfolio));
            customAssetRepository.delete(deleteCondition);
        }
    }

    private void deleteCoinsOnPortfolio(Portfolio existingPortfolio) {
        for (Coin ass : existingPortfolio.getCoins()) {
            CoinHib deleteCondition = new CoinHib(ass.getId());
            deleteCondition.setPortfolio(new PortfolioHib(existingPortfolio));
            coinRepository.delete(deleteCondition);
        }
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
