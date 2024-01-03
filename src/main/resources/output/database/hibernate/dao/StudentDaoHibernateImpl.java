package de.fhws.fiw.fds.implementation.server.database.hibernate.dao;

import de.fhws.fiw.fds.implementation.server.database.hibernate.models.StudentDB;
import de.fhws.fiw.fds.implementation.server.database.hibernate.operations.*;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.IDatabaseConnection;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import jakarta.persistence.EntityManagerFactory;

public class StudentDaoHibernateImpl implements StudentDaoHibernate {
    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    public StudentDaoHibernateImpl() {
        super();
    }

    @Override
    public CollectionModelHibernateResult<StudentDB> readByQuery(String firstName, String lastName, SearchParameter searchParameter) {
        return new StudentQueryOperation(emf, firstName, lastName, searchParameter).start();
    }

    @Override
    public NoContentResult create(StudentDB model) {
        return new StudentPersistOperation(emf, model).start();
    }

    @Override
    public SingleModelHibernateResult<StudentDB> readById(long id) {
        return new StudentReadByIdOperation(emf, id).start();
    }

    @Override
    public CollectionModelHibernateResult<StudentDB> readAll(SearchParameter searchParameter) {
        // TODO?
        return null;
    }

    @Override
    public NoContentResult update(StudentDB model) {
        return new StudentUpdateOperation(emf, model).start();
    }

    @Override
    public NoContentResult delete(long id) {
        return new StudentDeleteByIdOperation(emf, id).start();
    }
}