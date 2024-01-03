package ${basePackage}.server.database.hibernate.operations;

import ${basePackage}.server.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class ${name}ReadByIdOperation extends AbstractReadByIdOperation<${name}DB> {
    public ${name}ReadByIdOperation(EntityManagerFactory emf, long id) {
        super(emf, ${name}DB.class, id);
    }
}