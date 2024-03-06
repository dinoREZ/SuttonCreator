package ${basePackage}.server.api.queries;

import ${basePackage}.server.api.models.${name};
import ${basePackage}.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class ${name}Query extends AbstractQuery<${name}> {

    <#list query.attributes as attributeTriple>
    private ${attributeTriple.left} ${attributeTriple.middle};
    </#list>

    public ${name}Query(<#list query.attributes as attributeTriple>${attributeTriple.left} ${attributeTriple.middle}, </#list>int offset, int size) {
        <#list query.attributes as attributeTriple>
        this.${attributeTriple.middle} = ${attributeTriple.middle};
        </#list>
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<Course>(offset, size);
    }

    @Override
    protected CollectionModelResult<${name}> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().get<${name}>Dao().readBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(<#list query.attributes as attributeTriple>${attributeTriple.middle}, </#list>searchParameter);
    }
}
