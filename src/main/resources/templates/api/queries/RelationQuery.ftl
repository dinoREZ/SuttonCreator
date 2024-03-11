package ${basePackage}.api.queries;

import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class ${primaryName}${secondaryName}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Query extends AbstractRelationQuery<${secondaryName}> {
    <#list query.pathQueryParameters as queryParameter>
    private ${queryParameter.type} ${queryParameter.name};
    </#list>
    private boolean showAll;

    public ${primaryName}${secondaryName}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Query(long primaryId, boolean showAll, <#list query.pathQueryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}<#sep>, </#list>) {
        super(primaryId);
        <#list query.pathQueryParameters as queryParameter>
        this.${queryParameter.name} = ${queryParameter.name};
        </#list>
        this.showAll = showAll;
    }

    @Override
    protected CollectionModelResult<${secondaryName}> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        if(showAll) {
            return DaoFactory.getInstance().get${secondaryName}Dao().readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(<#list query.pathQueryParameters as queryParameter>${queryParameter.name}, </#list>searchParameter);
        } else {
            return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(this.primaryId, <#list query.pathQueryParameters as queryParameter>${queryParameter.name}, </#list>searchParameter);
        }
    }
}
