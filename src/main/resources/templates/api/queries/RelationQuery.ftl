package ${basePackage}.api.queries;

import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class ${primaryName}${secondaryName}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Query extends AbstractRelationQuery<${secondaryName}> {
    <#list query.queryParameters as queryParameter>
    private ${queryParameter.type} ${queryParameter.name};
    </#list>

    public ${primaryName}${secondaryName}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Query(long primaryId,  <#list query.pathQueryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>int offset, int size) {
        super(primaryId);
        <#list query.pathQueryParameters as queryParameter>
        this.${queryParameter.name} = ${queryParameter.name};
        </#list>
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<Course>(offset, size);
    }

    @Override
    protected CollectionModelResult<${secondaryName}> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(this.primaryId, <#list query.pathQueryParameters as queryParameter>${queryParameter.name}, </#list>searchParameter);
    }
}
