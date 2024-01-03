package ${basePackage}.server.database.hibernate.dao;

import ${basePackage}.server.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface ${name}DaoHibernate extends IDatabaseAccessObjectHibernate<${name}DB> {

    <#if hasQuery>CollectionModelHibernateResult<${name}> readByQuery(<#list queryAttributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter);</#if>

}