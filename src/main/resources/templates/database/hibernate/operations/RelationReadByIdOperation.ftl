package ${basePackage}.database.hibernate.operations;

import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractReadRelationByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${primaryName}${secondaryName}ReadByIdOperation extends AbstractReadRelationByIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    public ${primaryName}${secondaryName}ReadByIdOperation(EntityManagerFactory emf, long primaryId, long secondaryId) {
        super(emf, ${primaryName}${secondaryName}DB.class, primaryId, secondaryId);
    }
}
