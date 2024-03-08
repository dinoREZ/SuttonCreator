package ${basePackage}.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import ${basePackage}.api.models.${name};
import ${basePackage}.database.DaoFactory;

public class ${name}ReadAllQuery extends AbstractQuery<${name}> {
    @Override
    protected CollectionModelResult<${name}> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().get${name}Dao().readAll(searchParameter);
    }
}
