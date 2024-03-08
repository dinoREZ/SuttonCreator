package ${basePackage}.api.states;

<#list resources as resource>
import ${basePackage}.api.states.${resource.name}RelTypes;
import ${basePackage}.api.states.${resource.name}Uri;
</#list>
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetDispatcherState;

import java.util.List;

import static de.fhws.fiw.fds.sutton.server.AbstractDatabaseInstaller.RoleNames.GUEST_ROLES;

public class GetDispatcher extends AbstractGetDispatcherState {

    protected GetDispatcher(Builder builder) {
        super(builder);
    }

    @Override
    protected void defineTransitionLinks() {
        <#list resources as resource>
        <#list resource.states as state>
        <#if state.stateType == "GET_ALL">
        addLink(${resource.name}Uri.REL_PATH, ${resource.name}RelTypes.GET_ALL);
        </#if>
        </#list>
        </#list>
    }

    @Override
    protected List<String> getAllowedRoles() {
        return GUEST_ROLES;
    }

    public static class Builder extends AbstractDispatcherStateBuilder {
        @Override
        public GetDispatcher build() {
            return new GetDispatcher( this );
        }
    }
}
