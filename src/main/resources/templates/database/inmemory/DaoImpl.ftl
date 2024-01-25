package ${basePackage}.server.database.inmemory;

import ${basePackage}.server.api.models.${name};
import ${basePackage}.server.database.${name}Dao;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class ${name}DaoImpl extends AbstractInMemoryStorage<${name}> implements ${name}Dao {
    <#list queries as query>
    @Override
    public CollectionModelResult<${name}> readBy<#list query.attributes as attributeName, _>${attributeName?cap_first}</#list>(<#list query.attributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter) {
        return readByPredicate(
                ${name?lower_case} -> (
                    <#list query.attributes as attributeName, _>
                    ${name?lower_case}.get${attributeName?cap_first}().toLowerCase().contains(${attributeName}.toLowerCase())<#sep> &&
                    </#list>

                ),
                searchParameter);
    }
    </#list>
}