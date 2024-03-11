package ${basePackage}.database;

import ${basePackage}.api.models.${secondaryName};
import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface ${primaryName}${secondaryName}Dao extends IDatabaseRelationAccessObject<${secondaryName}> {
    <#list queries as query>
    CollectionModelResult<${secondaryName}> readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(long secondaryId, <#list query.queryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter);
    </#list>
}
