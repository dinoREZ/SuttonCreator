package ${basePackage}.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.DaoFactory;

public class ${primaryName}${secondaryName}ReadAllQuery extends AbstractRelationQuery<${secondaryName}> {

    private boolean showAll;

    public ${primaryName}${secondaryName}ReadAllQuery(long primaryId, boolean showAll) {
        super(primaryId);
        this.showAll = showAll;
    }

    @Override
    protected CollectionModelResult<${secondaryName}> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        if(showAll) {
            return DaoFactory.getInstance().get${secondaryName}Dao().readAll(searchParameter);
        } else {
            return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().readAll(this.primaryId, searchParameter);
        }
    }
}
