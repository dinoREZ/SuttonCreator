package ${basePackage}.database.inmemory;

import ${basePackage}.api.models.${name};
import ${basePackage}.database.${name}Dao;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class ${name}DaoImpl extends AbstractInMemoryStorage<${name}> implements ${name}Dao {
    <#list queries as query>
    @Override
    public CollectionModelResult<${name}> readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(<#list query.queryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter) {
        return readByPredicate(
                ${name?lower_case} -> (
                    <#list query.queryParameters as queryParameter>
                    ${name?lower_case}.get${queryParameter.name?cap_first}().toLowerCase().contains(${queryParameter.name}.toLowerCase())<#sep> &&
                    </#list>

                ),
                searchParameter);
    }
    </#list>
}