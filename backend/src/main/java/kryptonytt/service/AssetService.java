//package kryptonytt.service;
//
//import kryptonytt.domain.Portfolio;
//import kryptonytt.entity.AssetHib;
//import kryptonytt.entity.KryptonyttUserHib;
//import kryptonytt.entity.PortfolioHib;
//import kryptonytt.repository.AssetRepository;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.math.BigDecimal;
//
//@Service
//public class AssetService {
//
//    private final AssetRepository assetRepository;
//    private final PortfolioService portfolioService;
//    private UserService userService;
//
//    public AssetService(AssetRepository assetRepository, PortfolioService portfolioService, UserService userService) {
//        this.assetRepository = assetRepository;
//        this.portfolioService = portfolioService;
//        this.userService = userService;
//    }
//
//    @Transactional
//    public void createAsset(String name, Long portFolioId, BigDecimal amount) {
//        PortfolioHib portfolioExample = new PortfolioHib();
//        portfolioExample.setId(portFolioId);
//
//        assetRepository.save(new AssetHib(name, portfolioExample, amount));
//    }
//
//}
