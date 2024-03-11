package ${basePackage}.api.states;

<#list resources as resource>
import ${basePackage}.api.states.${resource.name}RelTypes;
import ${basePackage}.api.states.${resource.name}Uri;
</#list>
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;

import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class GetDispatcherState extends AbstractGetDispatcherState {

    protected GetDispatcherState(Builder builder) {
        super(builder);
    }

    @Override
    protected void defineTransitionLinks() {
        <#list resources as resource>
        addLink(${resource.name}Uri.REL_PATH, ${resource.name}RelTypes.GET_ALL);
        </#list>
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractDispatcherStateBuilder {
        @Override
        public GetDispatcherState build() {
            return new GetDispatcherState( this );
        }
    }
}
