package ${basePackage}.database.hibernate.operations;

import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteAllRelationsByPrimaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${primaryName}${secondaryName}DeleteByPrimaryIdOperation extends AbstractDeleteAllRelationsByPrimaryIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    public ${primaryName}${secondaryName}DeleteByPrimaryIdOperation(EntityManagerFactory emf, long primaryId) {
        super(emf, ${primaryName}${secondaryName}DB.class, primaryId);
    }
}
