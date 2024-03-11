package ${basePackage}.database.hibernate.dao;

import ${basePackage}.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface ${name}DaoHibernate extends IDatabaseAccessObjectHibernate<${name}DB> {

    <#list queries as query>
    CollectionModelHibernateResult<${name}DB> readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(<#list query.queryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter);
    </#list>

}