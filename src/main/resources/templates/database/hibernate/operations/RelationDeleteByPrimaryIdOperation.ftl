package ${basePackage}.server.database.hibernate.operations;

import ${basePackage}.server.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteAllRelationsByPrimaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${secondaryName}sOf${primaryName}DeleteByPrimaryIdOperation extends AbstractDeleteAllRelationsByPrimaryIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    public ${secondaryName}sOf${primaryName}DeleteByPrimaryIdOperation(EntityManagerFactory emf, long primaryId) {
        super(emf, ${primaryName}${secondaryName}DB.class, primaryId);
    }
}
