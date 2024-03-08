package ${basePackage}.database.inmemory;

import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.${secondaryName}sOf${primaryName}Dao;
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;

public class ${secondaryName}sOf${primaryName}DaoImplNew extends AbstractInMemoryRelationStorage<${secondaryName}> implements ${secondaryName}sOf${primaryName}Dao {

    <#list queries as query>
    @Override
    public CollectionModelResult<${secondaryName}> readBy<#list query.attributes as attributeName, _>${attributeName?cap_first}</#list>(long primaryId, <#list query.attributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter) {
        return readByPredicate(primaryId,
                    ${secondaryName?lower_case} -> (
                        <#list query.attributes as attributeName, _>
                        ${secondaryName?lower_case}.get${attributeName?cap_first}().toLowerCase().contains(${attributeName}.toLowerCase())<#sep> &&
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
