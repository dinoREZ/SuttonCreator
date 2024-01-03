package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractDeleteByIdOperation;
import jakarta.persistence.EntityManagerFactory;

public class StudentDeleteByIdOperation extends AbstractDeleteByIdOperation<StudentDB> {
    public StudentDeleteByIdOperation(EntityManagerFactory emf, long idToDelete) {
        super(emf, StudentDB.class, idToDelete);
    }
}