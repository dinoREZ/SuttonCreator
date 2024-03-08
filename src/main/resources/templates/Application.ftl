package ${basePackage};

import ${basePackage}.api.security.NoAuthNeededAuthenticationProvider;
<#list resources as resource>
import ${basePackage}.api.services.${resource.name}Service;
<#list resource.subResources as subResource>
import ${basePackage}.api.services.${subResource.name}Service;
</#list>
</#list>
import ${basePackage}.api.services.DispatcherService;
import de.fhws.fiw.fds.sutton.server.api.AbstractApplication;
import de.fhws.fiw.fds.sutton.server.api.security.IAuthenticationProvider;
import de.fhws.fiw.fds.sutton.server.api.security.SuttonAuthenticationProvider;
import org.apache.catalina.loader.ParallelWebappClassLoader;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class Application extends AbstractApplication {
    @Override
    protected AbstractBinder registerDependencyInjectionBinder() {
        return new AbstractBinder() {
            @Override
            protected void configure() {
                bind(NoAuthNeededAuthenticationProvider.class).to(IAuthenticationProvider.class);
            }
        };
    }

    @Override
    protected Set<Class<?>> getServiceClasses() {
        ParallelWebappClassLoader classloader = (ParallelWebappClassLoader) this.getClass().getClassLoader();
        classloader.setDelegate(true);

        final Set<Class<?>> returnValue = new HashSet<>();

        <#list resources as resource>
        returnValue.add(${resource.name}Service.class);
        </#list>
        returnValue.add(DispatcherService.class);

        return returnValue;
    }
}
