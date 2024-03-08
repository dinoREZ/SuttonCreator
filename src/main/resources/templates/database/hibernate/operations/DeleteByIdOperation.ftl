package ${basePackage}.database.hibernate.operations;

import ${basePackage}.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractDeleteByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${name}DeleteByIdOperation extends AbstractDeleteByIdOperation<${name}DB> {
    public ${name}DeleteByIdOperation(EntityManagerFactory emf, long idToDelete) {
        super(emf, ${name}DB.class, idToDelete);
    }
}