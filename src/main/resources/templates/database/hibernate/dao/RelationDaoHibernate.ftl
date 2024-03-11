package ${basePackage}.database.hibernate.dao;

import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseRelationAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface ${primaryName}${secondaryName}DaoHibernate extends IDatabaseRelationAccessObjectHibernate<${secondaryName}DB> {
    <#list queries as query>
    CollectionModelHibernateResult<${secondaryName}DB> readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(long primaryId, <#list query.queryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter);
    </#list>
}
