package ${basePackage}.api.rateLimiting;

import de.fhws.fiw.fds.sutton.server.api.rateLimiting.RateLimiter;

public class AnyApiKeyRateLimiter extends RateLimiter {

    public static final AnyApiKeyRateLimiter anyApiKeyRateLimiter = new AnyApiKeyRateLimiter();

    public AnyApiKeyRateLimiter() {
        super(5);
    }

    @Override
    public boolean isRequestAllowed(String apiKey) {
        return true;
    }
}
