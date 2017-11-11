package kryptonytt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AssetLimitReached extends RuntimeException {
    public AssetLimitReached() {
        super("The maximum number of assets for this user has been exceeded, the maximum combined assets is limited to 100 per user");
    }
}
