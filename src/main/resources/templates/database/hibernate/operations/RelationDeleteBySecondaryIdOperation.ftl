package ${basePackage}.server.database.hibernate.operations;

import ${basePackage}.server.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteAllRelationsBySecondaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${secondaryName}sOf${primaryName}DeleteBySecondaryIdOperation extends AbstractDeleteAllRelationsBySecondaryIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    public ${secondaryName}sOf${primaryName}DeleteBySecondaryIdOperation(EntityManagerFactory emf, long secondaryId) {
        super(emf, ${primaryName}${secondaryName}DB.class, secondaryId);
    }
}
