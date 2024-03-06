package ${basePackage}.server.api.states;

import ${basePackage}.server.api.models.${secondaryName};
import ${basePackage}.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.states.AbstractState;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;

import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class Post${primaryName}${secondaryName}State extends AbstractPostRelationState<${secondaryName}> {
    public Post${primaryName}${secondaryName}State(Builder builder) {
        super(builder);
    }

    @Override
    protected void authorizeRequest() {

    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().create(this.primaryId, this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {

    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractPostRelationStateBuilder<${secondaryName}> {

        @Override
        public AbstractState build() {
            return new Post${primaryName}${secondaryName}State(this);
        }
    }
}
