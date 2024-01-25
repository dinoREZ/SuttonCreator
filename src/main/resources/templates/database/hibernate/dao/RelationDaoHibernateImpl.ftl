package ${basePackage}.server.database.hibernate.dao;

import ${basePackage}.server.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.server.database.hibernate.operations.*;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class ${secondaryName}sOf${primaryName}sDaoHibernateImpl implements ${secondaryName}sOf${primaryName}sDaoHibernate {
    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    <#list queries as query>
    @Override
    public CollectionModelHibernateResult<${secondaryName}DB> readBy<#list query.attributes as attributeName, _>${attributeName?cap_first}</#list>(long primaryId, <#list query.attributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter) {
        return new ${secondaryName}sOf${primaryName}ByQueryOperation(emf, primaryId, <#list query.attributes as attributeName, _>${attributeName}, </#list>searchParameter).start();
    }

    </#list>
    @Override
    public NoContentResult create(long primaryId, ${secondaryName}DB secondaryModel) {
        return new ${secondaryName}sOf${primaryName}sCreateOperation(emf, primaryId, secondaryModel).start();
    }

    @Override
    public NoContentResult update(long primaryId, ${secondaryName}DB secondaryModel) {
        return new ${secondaryName}Of${primaryName}UpdateOperation(emf, primaryId, secondaryModel).start();
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return new ${secondaryName}sOf${primaryName}sDeleteOperation(emf, primaryId, secondaryId).start();
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return new ${primaryName}sOf${secondaryName}DeleteByIdOperation(emf, primaryId).start();
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return new ${secondaryName}sOf${primaryName}DeleteByIdOperation(emf, secondaryId).start();
    }

    @Override
    public SingleModelHibernateResult<${secondaryName}DB> readById(long primaryId, long secondaryId) {
        return new ${secondaryName}Of${primaryName}ByIdOperation(emf, primaryId, secondaryId).start();
    }

    @Override
    public CollectionModelHibernateResult<${secondaryName}DB> readAll(long primaryId, SearchParameter searchParameter) {
        return new ${secondaryName}sOf${primaryName}ReadAllOperation(emf, primaryId, searchParameter).start();
    }
}
