package ${basePackage}.server.api.states;

import ${basePackage}.server.api.models.${secondaryName};
import ${basePackage}.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.EntityTag;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Put${primaryName}${secondaryName}State extends AbstractPutRelationState<${secondaryName}> {
    public Put${primaryName}${secondaryName}State(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<${secondaryName}> loadModel() {
        return DaoFactory.getInstance().get${secondaryName}Dao().readById(requestedId);
    }

    @Override
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().update(primaryId, modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        <#list states as state>
        <#if state.stateType == "GET">
        addLink(${primaryName}${secondaryName}Uri.REL_PATH_ID, ${primaryName}${secondaryName}RelTypes.GET, primaryId, requestedId);
        </#if>
        </#list>
    }

    <#if useEtags>
    @Override
    protected boolean clientDoesNotKnowCurrentModelState(final AbstractModel modelFromDatabase) {
        EntityTag entityTag = EtagGenerator.createEntityTag(modelFromDatabase);
        return this.request.evaluatePreconditions(entityTag) == null;
    }

    </#if>
    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractPutRelationStateBuilder<${secondaryName}> {

        @Override
        public AbstractState build() {
            return new Put${primaryName}${secondaryName}State(this);
        }
    }
}
