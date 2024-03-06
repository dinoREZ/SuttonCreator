package ${basePackage}.server.api.states;

import ${basePackage}.server.api.models.${name};
import ${basePackage}.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.Response;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Get${name}State extends AbstractGetState<${name}> {

    public Get${name}State(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<${name}> loadModel() {
        return DaoFactory.getInstance().get${name}Dao().readById(requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        <#list states as state>
        <#if state.stateType == "PUT">
        addLink(${name}Uri.REL_PATH_ID, ${name}RelTypes.UPDATE, this.requestedId);
        </#if>
        <#if state.stateType == "DELETE">
        addLink(${name}Uri.REL_PATH_ID, ${name}RelTypes.DELETE, this.requestedId);
        </#if>
        </#list>
    }

    <#if useEtags>
    @Override
    protected boolean clientKnowsCurrentModelState( final AbstractModel modelFromDatabase ) {
        EntityTag entityTag = EtagGenerator.createEntityTag(modelFromDatabase);
        return this.request.evaluatePreconditions(entityTag) != null;
    }

    </#if>
    <#if (cacheControl??)>
    @Override
    protected void defineHttpCaching() {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(${cacheControl.isNoCache()?c});
        cacheControl.setSMaxAge(${cacheControl.getSMaxAge()});
        cacheControl.setMaxAge(${cacheControl.getMaxAge()});
        cacheControl.setPrivate(${cacheControl.isPrivate()?c});
        cacheControl.setNoStore(${cacheControl.isNoStore()?c});
        cacheControl.setMustRevalidate(${cacheControl.isMustRevalidate()?c});
        cacheControl.setNoTransform(${cacheControl.isNoTransform()?c});
        cacheControl.setProxyRevalidate(${cacheControl.isProxyRevalidate()?c});
        this.responseBuilder.cacheControl(cacheControl);
        <#if useEtags>
        this.responseBuilder.tag(EtagGenerator.createEntityTag(this.requestedModel.getResult()));
        </#if>
    }

    </#if>
    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractGetStateBuilder {
        @Override
        public AbstractState build() {
            return new Get${name}State( this );
        }
    }
}
