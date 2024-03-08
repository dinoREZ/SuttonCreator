package ${basePackage}.api.services;

import ${basePackage}.api.rateLimiting.AnyApiKeyRateLimiter;
import ${basePackage}.api.states.GetDispatcherState;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("")
public class DispatcherService extends AbstractService {

    @GET
    public Response getDefault() {
        return new GetDispatcherState.Builder()
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }
}
