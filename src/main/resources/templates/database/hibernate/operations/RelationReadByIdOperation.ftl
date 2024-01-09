package ${basePackage}.server.database.hibernate.operations;

import ${basePackage}.server.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractReadRelationByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${secondaryName}Of${primaryName}ByIdOperation extends AbstractReadRelationByIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    public ${secondaryName}Of${primaryName}ByIdOperation(EntityManagerFactory emf, long primaryId, long secondaryId) {
        super(emf, ${primaryName}${secondaryName}DB.class, primaryId, secondaryId);
    }
}
