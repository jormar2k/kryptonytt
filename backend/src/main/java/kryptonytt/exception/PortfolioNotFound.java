package kryptonytt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PortfolioNotFound extends RuntimeException {
    public PortfolioNotFound(String name, String username) {
        super("Portfolio '" + name + "' for '" + username + "' not found.");
    }
}
