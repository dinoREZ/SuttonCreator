package ${basePackage}.api.states;

import ${basePackage}.api.models.${secondaryName};
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;

import javax.ws.rs.core.GenericEntity;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Get${primaryName}${secondaryName}CollectionState extends AbstractGetCollectionRelationState<${secondaryName}> {
    public Get${primaryName}${secondaryName}CollectionState(Builder builder) {
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
        addLink(${primaryName}${secondaryName}Uri.REL_PATH, ${primaryName}${secondaryName}RelTypes.CREATE, primaryId);
        addLink(${primaryName}${secondaryName}Uri.REL_PATH_SHOW_ALL, ${primaryName}${secondaryName}RelTypes.GET_ALL, primaryId);
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractGetCollectionRelationStateBuilder<${secondaryName}> {
        @Override
        public AbstractState build() {
            return new Get${primaryName}${secondaryName}CollectionState(this);
        }
    }
}
