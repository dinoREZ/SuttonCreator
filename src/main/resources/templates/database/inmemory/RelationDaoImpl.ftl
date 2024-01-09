package ${basePackage}.server.database.inmemory;

import ${basePackage}.server.api.models.${secondaryName};
import ${basePackage}.server.database.${secondaryName}sOf${primaryName}Dao;
import ${basePackage}.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;

public class ${secondaryName}sOf${primaryName}DaoImplNew extends AbstractInMemoryRelationStorage<${secondaryName}> implements ${secondaryName}sOf${primaryName}Dao {

    @Override
    public CollectionModelResult<${secondaryName}> readByQuery(long primaryId, String name, SearchParameter searchParameter) {
        return readByPredicate(primaryId,
                    ${secondaryName?lower_case} -> (
                        <#list queryAttributes as attributeName, _>
                        ${secondaryName?lower_case}.get${attributeName?cap_first}().toLowerCase().contains(${attributeName}.toLowerCase())<#sep> &&
                        </#list>

                    );
    }

    @Override
    public CollectionModelResult<${secondaryName}> readAll(long primaryId, SearchParameter searchParameter) {
        return readByPredicate(primaryId, ${secondaryName?lower_case} -> true);
    }

    @Override
    protected IDatabaseAccessObject<${secondaryName}> getSecondaryStorage() {
        return DaoFactory.getInstance().get${secondaryName}Dao();
    }
}
