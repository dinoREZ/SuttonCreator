package ${basePackage}.database.hibernate.operations;

import ${basePackage}.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractPersistOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${name}PersistOperation extends AbstractPersistOperation<${name}DB> {
    public ${name}PersistOperation(EntityManagerFactory emf, ${name}DB model) {
        super(emf, model);
    }
}