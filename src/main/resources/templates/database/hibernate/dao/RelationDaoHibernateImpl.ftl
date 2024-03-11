package ${basePackage}.database.hibernate.dao;

import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.database.hibernate.operations.*;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class ${primaryName}${secondaryName}DaoHibernateImpl implements ${primaryName}${secondaryName}DaoHibernate {
    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    <#list queries as query>
    @Override
    public CollectionModelHibernateResult<${secondaryName}DB> readBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(long primaryId, <#list query.queryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter) {
        return new  ${primaryName}${secondaryName}QueryBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Operation(emf, primaryId, <#list query.queryParameters as queryParameter>${queryParameter.name}, </#list>searchParameter).start();
    }

    </#list>
    @Override
    public NoContentResult create(long primaryId, ${secondaryName}DB secondaryModel) {
        return new ${primaryName}${secondaryName}PersistOperation(emf, primaryId, secondaryModel).start();
    }

    @Override
    public NoContentResult update(long primaryId, ${secondaryName}DB secondaryModel) {
        return new ${primaryName}${secondaryName}UpdateOperation(emf, primaryId, secondaryModel).start();
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return new ${primaryName}${secondaryName}DeleteByIdOperation(emf, primaryId, secondaryId).start();
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return new ${primaryName}${secondaryName}DeleteByPrimaryIdOperation(emf, primaryId).start();
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return new ${primaryName}${secondaryName}DeleteBySecondaryIdOperation(emf, secondaryId).start();
    }

    @Override
    public SingleModelHibernateResult<${secondaryName}DB> readById(long primaryId, long secondaryId) {
        return new ${primaryName}${secondaryName}ReadByIdOperation(emf, primaryId, secondaryId).start();
    }

    @Override
    public CollectionModelHibernateResult<${secondaryName}DB> readAll(long primaryId, SearchParameter searchParameter) {
        return new ${primaryName}${secondaryName}ReadAllOperation(emf, primaryId, searchParameter).start();
    }
}
