package ${basePackage}.api.states;

import ${basePackage}.api.models.${name};
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;

import javax.ws.rs.core.GenericEntity;
import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Get${name}CollectionState extends AbstractGetCollectionState<${name}> {
    protected Get${name}CollectionState(Builder builder) {
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
        addLink(${name}Uri.REL_PATH, ${name}RelTypes.CREATE);
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractGetCollectionStateBuilder<${name}> {
        @Override
        public AbstractState build() {
            return new Get${name}CollectionState(this);
        }
    }
}
