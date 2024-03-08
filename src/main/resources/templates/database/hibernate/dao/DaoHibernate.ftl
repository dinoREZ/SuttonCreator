package ${basePackage}.database.hibernate.dao;

import ${basePackage}.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.dao.IDatabaseAccessObjectHibernate;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;

public interface ${name}DaoHibernate extends IDatabaseAccessObjectHibernate<${name}DB> {

    <#list queries as query>
    CollectionModelHibernateResult<${name}> readBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(<#list query.attributes as attributeTriple>${attributeTriple.left} ${attributeTriple.middle}, </#list>SearchParameter searchParameter);
    </#list>

}