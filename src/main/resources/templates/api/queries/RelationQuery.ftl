package ${basePackage}.api.queries;

import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class ${primaryName}${secondaryName}Query extends AbstractRelationQuery<${secondaryName}> {
    <#list query.attributes as attributeTriple>
    private ${attributeTriple.left} ${attributeTriple.middle};
    </#list>

    public ${primaryName}${secondaryName}Query(long primaryId,  <#list query.attributes as attributeTriple>${attributeTriple.left} ${attributeTriple.middle}, </#list>int offset, int size) {
        super(primaryId);
        <#list query.attributes as attributeTriple>
        this.${attributeTriple.middle} = ${attributeTriple.middle};
        </#list>
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<Course>(offset, size);
    }

    @Override
    protected CollectionModelResult<${secondaryName}> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().get${primaryName}${secondaryName}Dao().readBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(this.primaryId, <#list query.attributes as attributeTriple>${attributeTriple.middle}, </#list>searchParameter);
    }
}
