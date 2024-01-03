package ${basePackage}.server.database;

import ${basePackage}.server.api.models.${name};
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public interface ${name}Dao extends IDatabaseAccessObject<${name}> {
    <#if hasQuery>CollectionModelResult<${name}> readByQuery(<#list queryAttributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter);</#if>
}