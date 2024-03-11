package ${basePackage}.database;

import ${basePackage}.api.models.${name};
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface ${name}Dao extends IDatabaseAccessObject<${name}> {
    <#list queries as query>
    CollectionModelResult<${name}> readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(<#list query.pathQueryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter);
    </#list>
}