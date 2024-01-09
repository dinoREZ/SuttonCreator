package ${basePackage}.server.database.hibernate.operations.coursesOf${primaryName};

import ${basePackage}.server.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractPersistRelationByPrimaryIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${secondaryName}sOf${primaryName}sCreateOperation extends AbstractPersistRelationByPrimaryIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    public ${secondaryName}sOf${primaryName}sCreateOperation(EntityManagerFactory emf, long primaryId, ${secondaryName}DB courseDB) {
        super(emf, ${primaryName}${secondaryName}DB.class, ${primaryName}DB.class, primaryId, courseDB);
    }
}
