package ${basePackage}.database.inmemory;

import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.${primaryName}${secondaryName}Dao;
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;

public class ${primaryName}${secondaryName}DaoImpl extends AbstractInMemoryRelationStorage<${secondaryName}> implements ${primaryName}${secondaryName}Dao {

    <#list queries as query>
    @Override
    public CollectionModelResult<${secondaryName}> readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(long primaryId, <#list query.queryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter) {
        return readByPredicate(primaryId,
                    ${secondaryName?lower_case} -> (
                        <#list query.queryParameters as queryParameter>
                        ${secondaryName?lower_case}.get${queryParameter.name?cap_first}().toLowerCase().contains(${queryParameter.name}.toLowerCase())<#sep> &&
                        </#list>

                    );
    }
    </#list>

    @Override
    public CollectionModelResult<${secondaryName}> readAll(long primaryId, SearchParameter searchParameter) {
        return readByPredicate(primaryId, ${secondaryName?lower_case} -> true);
    }

    @Override
    protected IDatabaseAccessObject<${secondaryName}> getSecondaryStorage() {
        return DaoFactory.getInstance().get${secondaryName}Dao();
    }
}
