package ${basePackage}.database.hibernate.operations.coursesOf${primaryName};

import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteRelationByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${secondaryName}sOf${primaryName}sDeleteOperation extends AbstractDeleteRelationByIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    public ${secondaryName}sOf${primaryName}sDeleteOperation(EntityManagerFactory emf, long primaryId, long secondaryId) {
        super(emf, ${primaryName}${secondaryName}DB.class, primaryId, secondaryId);
    }
}
