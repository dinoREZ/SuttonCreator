package ${basePackage}.database.hibernate.dao;

import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseRelationAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface ${primaryName}${secondaryName}DaoHibernate extends IDatabaseRelationAccessObjectHibernate<${secondaryName}DB> {
    <#list queries as query>
    CollectionModelHibernateResult<${secondaryName}DB> readBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(long primaryId, <#list query.attributes as attributeTriple>${attributeTriple.left} ${attributeTriple.middle}, </#list>SearchParameter searchParameter);
    </#list>
}
