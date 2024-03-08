package ${basePackage}.database.hibernate.operations;

import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractUpdateRelationByPrimaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${primaryName}${secondaryName}UpdateOperation extends AbstractUpdateRelationByPrimaryIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    public ${primaryName}${secondaryName}UpdateOperation(EntityManagerFactory emf, long primaryId, ${secondaryName}DB secondaryModelToUpdate) {
        super(emf, ${primaryName}${secondaryName}DB.class, ${primaryName}DB.class, primaryId, secondaryModelToUpdate);
    }
}