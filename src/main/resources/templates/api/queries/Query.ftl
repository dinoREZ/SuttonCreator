package ${basePackage}.api.queries;

import ${basePackage}.api.models.${name};
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class ${name}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Query extends AbstractQuery<${name}> {

    <#list query.queryParameters as queryParameter>
    private ${queryParameter.type} ${queryParameter.name};
    </#list>

    public ${name}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Query(<#list query.pathQueryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>int offset, int size) {
        <#list query.pathQueryParameters as queryParameter>
        this.${queryParameter.name} = ${queryParameter.name};
        </#list>
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<${name}>(offset, size);
    }

    @Override
    protected CollectionModelResult<${name}> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().get${name}Dao().readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(<#list query.pathQueryParameters as queryParameter>${queryParameter.name}, </#list>searchParameter);
    }
}
