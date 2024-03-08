package ${basePackage}.api.states;

import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.CachingUtils;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Get${primaryName}${secondaryName}State extends AbstractGetRelationState<${secondaryName}> {
    public Get${primaryName}${secondaryName}State(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<${secondaryName}> loadModel() {
        return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().readById(primaryId, requestedId);
    }

    @Override
    protected void defineTransitionLinks() {
        <#list states as state>
        <#if state.stateType == "GET_ALL">
        addLink(${primaryName}${secondaryName}Uri.REL_PATH, ${primaryName}${secondaryName}RelTypes.GET_ALL_LINKED, primaryId);
        </#if>
        <#if state.stateType == "PUT">
        addLink(${primaryName}${secondaryName}Uri.REL_PATH_ID, ${primaryName}${secondaryName}RelTypes.UPDATE, primaryId, requestedId);
        </#if>
        <#if state.stateType == "DELETE">
        addLink(${primaryName}${secondaryName}Uri.REL_PATH_ID, ${primaryName}${secondaryName}RelTypes.DELETE_LINK, primaryId, requestedId);
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

    public static class Builder extends AbstractGetRelationStateBuilder {
        @Override
        public AbstractState build() {
            return new Get${primaryName}${secondaryName}State(this);
        }
    }
}
