package ${basePackage}.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.DaoFactory;

public class ${primaryName}${secondaryName}ReadAllQuery extends AbstractRelationQuery<${secondaryName}> {

    public ${primaryName}${secondaryName}ReadAllQuery(long primaryId) {
        super(primaryId);
    }

    @Override
    protected CollectionModelResult<${secondaryName}> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().readAll(this.primaryId, searchParameter);
    }
}
