package ${basePackage}.database.inmemory;

import ${basePackage}.api.models.${name};
import ${basePackage}.database.${name}Dao;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class ${name}DaoImpl extends AbstractInMemoryStorage<${name}> implements ${name}Dao {
    <#list queries as query>
    @Override
    public CollectionModelResult<${name}> readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(<#list query.pathQueryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter) {
        return readByPredicate(
                ${name?lower_case} -> (
                    <#list query.queryParameters as queryParameter>
                    <#if queryParameter.comparisonType == "likeIgnoreCase">${name?lower_case}.get${queryParameter.name?cap_first}().toLowerCase().contains(<#if queryParameter.pathParameter>${queryParameter.name}<#else>${queryParameter.defaultValue}</#if>.toLowerCase())</#if><#if queryParameter.comparisonType == "like">${name?lower_case}.get${queryParameter.name?cap_first}().contains(<#if queryParameter.pathParameter>${queryParameter.name}<#else>${queryParameter.defaultValue}</#if>)</#if><#if queryParameter.comparisonType == "equal">java.util.Objects.equals(${name?lower_case}.get${queryParameter.name?cap_first}(), <#if queryParameter.pathParameter>${queryParameter.name}<#else>${queryParameter.defaultValue}</#if>)</#if><#if queryParameter.comparisonType == "greaterThan">${name?lower_case}.get${queryParameter.name?cap_first}() > <#if queryParameter.pathParameter>${queryParameter.name}<#else>${queryParameter.defaultValue}</#if></#if><#if queryParameter.comparisonType == "greaterThanOrEqualTo">${name?lower_case}.get${queryParameter.name?cap_first}() >= <#if queryParameter.pathParameter>${queryParameter.name}<#else>${queryParameter.defaultValue}</#if></#if><#if queryParameter.comparisonType == "lessThan">${name?lower_case}.get${queryParameter.name?cap_first}() < <#if queryParameter.pathParameter>${queryParameter.name}<#else>${queryParameter.defaultValue}</#if></#if><#if queryParameter.comparisonType == "lessThanOrEqualTo">${name?lower_case}.get${queryParameter.name?cap_first}() <= <#if queryParameter.pathParameter>${queryParameter.name}<#else>${queryParameter.defaultValue}</#if></#if><#sep> &&
                    </#list>

                ),searchParameter);
    }
    </#list>
}