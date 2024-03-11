package ${basePackage}.api.states;

import ${basePackage}.api.models.${name};
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.caching.EtagGenerator;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.put.AbstractPutState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;

import javax.ws.rs.core.EntityTag;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Put${name}State extends AbstractPutState<${name}> {
    protected Put${name}State(Builder builder) {
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
    protected NoContentResult updateModel() {
        return DaoFactory.getInstance().get${name}Dao().update(modelToUpdate);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(${name}Uri.REL_PATH_ID, ${name}RelTypes.GET, requestedId);
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

    public static class Builder extends AbstractPutStateBuilder<${name}> {
        @Override
        public AbstractState build() {
            return new Put${name}State(this);
        }
    }
}
