package ${basePackage}.database.hibernate.operations;

import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteAllRelationsBySecondaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${primaryName}${secondaryName}DeleteBySecondaryIdOperation extends AbstractDeleteAllRelationsBySecondaryIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    public ${primaryName}${secondaryName}DeleteBySecondaryIdOperation(EntityManagerFactory emf, long secondaryId) {
        super(emf, ${primaryName}${secondaryName}DB.class, secondaryId);
    }
}
