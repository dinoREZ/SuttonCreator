package ${basePackage}.api.states;

import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Delete${primaryName}${secondaryName}State extends AbstractDeleteRelationState<${secondaryName}> {
    public Delete${primaryName}${secondaryName}State(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<${secondaryName}> loadModel() {
        return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().readById(primaryId, modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().deleteRelation(primaryId, modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        <#list states as state>
        <#if state.stateType == "GET_ALL">
        addLink(${primaryName}${secondaryName}Uri.REL_PATH, ${primaryName}${secondaryName}RelTypes.GET_ALL_LINKED, primaryId);
        </#if>
        </#list>
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractDeleteRelationStateBuilder {

        @Override
        public AbstractState build() {
            return new Delete${primaryName}${secondaryName}State(this);
        }
    }
}
