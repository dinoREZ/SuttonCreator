package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class StudentReadByIdOperation extends AbstractReadByIdOperation<StudentDB> {
    public StudentReadByIdOperation(EntityManagerFactory emf, long id) {
        super(emf, StudentDB.class, id);
    }
}