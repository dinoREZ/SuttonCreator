package ${basePackage}.database.hibernate.operations;

import ${basePackage}.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractUpdateOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${name}UpdateOperation extends AbstractUpdateOperation<${name}DB> {
    public ${name}UpdateOperation(EntityManagerFactory emf, ${name}DB model) {
        super(emf, model);
    }
}