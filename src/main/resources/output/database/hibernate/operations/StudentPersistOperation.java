package de.fhws.fiw.fds.implementation.server.database.hibernate.operations;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractPersistOperation;
import jakarta.persistence.EntityManagerFactory;

public class StudentPersistOperation extends AbstractPersistOperation<StudentDB> {
    public StudentPersistOperation(EntityManagerFactory emf, StudentDB model) {
        super(emf, model);
    }
}