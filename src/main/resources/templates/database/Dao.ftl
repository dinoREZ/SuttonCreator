package ${basePackage}.server.database;

import ${basePackage}.server.api.models.${name};
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface ${name}Dao extends IDatabaseAccessObject<${name}> {
    <#list queries as query>
    CollectionModelResult<${name}> readBy<#list query.attributes as attributeName, _>${attributeName?cap_first}</#list>(<#list query.attributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter);
    </#list>
}