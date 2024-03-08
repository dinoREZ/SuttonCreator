package ${basePackage}.api.states;

import ${basePackage}.api.models.${name};
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Post${name}State extends AbstractPostState<${name}> {
    protected Post${name}State(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().get${name}Dao().create(modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractPostStateBuilder<${name}> {
        @Override
        public AbstractState build() {
            return new Post${name}State(this);
        }
    }
}
