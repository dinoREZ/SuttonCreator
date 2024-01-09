package ${basePackage}.server.database.hibernate.dao;

import ${basePackage}.server.database.hibernate.models.${secondaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseRelationAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface ${secondaryName}sOf${primaryName}sDaoHibernate extends IDatabaseRelationAccessObjectHibernate<${secondaryName}DB> {
    <#if hasQuery>CollectionModelHibernateResult<${secondaryName}DB> readByQuery(long primaryId, <#list queryAttributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter);</#if>
}
