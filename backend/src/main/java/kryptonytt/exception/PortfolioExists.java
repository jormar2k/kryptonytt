package kryptonytt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PortfolioExists extends RuntimeException {
    public PortfolioExists(String name, String username) {
        super("Portfolio '" + name + "' for '" + username + "' already exists.");
    }
}
