package ${basePackage}.server.database;

import ${basePackage}.server.api.models.${secondaryName};
import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface ${secondaryName}Of${primaryName}Dao extends IDatabaseRelationAccessObject<${secondaryName}> {
    <#list queries as query>
    CollectionModelResult<${secondaryName}> readBy<#list query.attributes as attributeName, _>${attributeName?cap_first}</#list>(long secondaryId, <#list query.attributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter);
    </#list>
}
