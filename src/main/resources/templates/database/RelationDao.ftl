package ${basePackage}.server.database;

import ${basePackage}.server.api.models.${secondaryName};
import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface ${secondaryName}Of${primaryName}Dao extends IDatabaseRelationAccessObject<${secondaryName}> {
    <#if hasQuery>CollectionModelResult<${secondaryName}> readByQuery(long secondaryId, <#list queryAttributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter);</#if>
}
