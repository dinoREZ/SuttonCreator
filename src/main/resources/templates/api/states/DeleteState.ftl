package ${basePackage}.api.states;

import ${basePackage}.api.models.${name};
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.delete.AbstractDeleteState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Delete${name}State extends AbstractDeleteState<${name}> {
    public Delete${name}State(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected SingleModelResult<${name}> loadModel() {
        return DaoFactory.getInstance().get${name}Dao().readById(modelIdToDelete);
    }

    @Override
    protected NoContentResult deleteModel() {
        <#list resources as resource>
        <#list resource.subResources as subResource>
        <#if resource.name == name>
        DaoFactory.getInstance().get${resource.name}${subResource.name}Dao().deleteRelationsFromPrimary(modelIdToDelete);
        </#if>
        <#if subResource.name == name>
        DaoFactory.getInstance().get${resource.name}${subResource.name}Dao().deleteRelationsToSecondary(modelIdToDelete);
        </#if>
        </#list>
        </#list>
        return DaoFactory.getInstance().get${name}Dao().delete(modelIdToDelete);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(${name}Uri.REL_PATH, ${name}RelTypes.GET_ALL);
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractDeleteStateBuilder {
        @Override
        public AbstractState build() {
            return new Delete${name}State(this);
        }
    }
}
