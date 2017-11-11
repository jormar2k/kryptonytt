package kryptonytt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PortfolioLimitReached extends RuntimeException {
    public PortfolioLimitReached() {
        super("The maximum number of portfolios for this user has been exceeded, the maximum number of portfolios is limited to 10 per user");
    }
}
