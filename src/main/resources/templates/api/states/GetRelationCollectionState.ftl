package ${basePackage}.server.api.states;

import ${basePackage}.server.api.models.${secondaryName};
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;

import javax.ws.rs.core.GenericEntity;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Get${primaryName}${secondaryName}State extends AbstractGetCollectionRelationState<${secondaryName}> {
    public Get${primaryName}${secondaryName}State(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected void defineHttpResponseBody() {
        this.responseBuilder.entity(new GenericEntity<>(this.result.getResult()) {
        });
    }

    @Override
    protected void defineTransitionLinks() {
        <#list states as state>
        <#if state.stateType == "POST">
        addLink(${primaryName}${secondaryName}Uri.REL_PATH, ${primaryName}${secondaryName}RelTypes.CREATE, primaryId);
        </#if>
        </#list>
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractGetCollectionRelationStateBuilder<${secondaryName}> {
        @Override
        public AbstractState build() {
            return new Get${primaryName}${secondaryName}State(this);
        }
    }
}
