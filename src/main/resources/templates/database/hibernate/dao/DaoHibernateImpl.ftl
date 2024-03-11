package ${basePackage}.database.hibernate.dao;

import ${basePackage}.database.hibernate.models.${name}DB;
import ${basePackage}.database.hibernate.operations.*;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class ${name}DaoHibernateImpl implements ${name}DaoHibernate {
    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    public ${name}DaoHibernateImpl() {
        super();
    }

    <#list queries as query>
    @Override
    public CollectionModelHibernateResult<${name}DB> readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(<#list query.pathQueryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter) {
        return new ${name}QueryBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Operation(emf, <#list query.pathQueryParameters as queryParameter>${queryParameter.name}, </#list>searchParameter).start();
    }
    </#list>

    @Override
    public NoContentResult create(${name}DB model) {
        return new ${name}PersistOperation(emf, model).start();
    }

    @Override
    public SingleModelHibernateResult<${name}DB> readById(long id) {
        return new ${name}ReadByIdOperation(emf, id).start();
    }

    @Override
    public CollectionModelHibernateResult<${name}DB> readAll(SearchParameter searchParameter) {
        return new ${name}ReadAllOperation(emf, searchParameter).start();
    }

    @Override
    public NoContentResult update(${name}DB model) {
        return new ${name}UpdateOperation(emf, model).start();
    }

    @Override
    public NoContentResult delete(long id) {
        return new ${name}DeleteByIdOperation(emf, id).start();
    }
}