public class StudentDaoAdapter implements StudentDao {
    private final StudentDaoHibernate dao = new StudentDaoHibernateImpl();

    @Override
    public NoContentResult create(Student model) {
        final StudentDB dbModel = createFrom(model);
        final NoContentResult returnValue = this.dao.create(dbModel);
        model.setId(dbModel.getId());
        return returnValue;
    }

    @Override
    public SingleModelResult<Student> readById(long id) {
        SingleModelHibernateResult<StudentDB> result = this.dao.readById(id);
        if(result.isEmpty()) {
            return new SingleModelResult<>();
        }

        if(result.hasError()) {
            final SingleModelResult<Student> returnValue = new SingleModelResult<>( );
            returnValue.setError( );
            return returnValue;
        }
        else {
            return new SingleModelResult<>(createFrom(result.getResult()));
        }
    }

    @Override
    public CollectionModelResult<Student> readAll(SearchParameter searchParameter) {
        // TODO
        return null;
    }

    @Override
    public CollectionModelResult<Student> readByQuery(String firstName, String lastName, SearchParameter searchParameter) {
        CollectionModelHibernateResult<StudentDB> result = dao.readByQuery(firstName, lastName, searchParameter);

        CollectionModelResult<Student> returnValue;
        if(result.hasError()) {
            returnValue = new CollectionModelResult<>();
            returnValue.setError();

        }
        else {
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(this::createFrom).collect(Collectors.toList()));
        }
        return returnValue;
    }

    @Override
    public NoContentResult update(Student model) {
        return dao.update(createFrom(model));
    }

    @Override
    public NoContentResult delete(long id) {
        return dao.delete(id);
    }

    private StudentDB createFrom(Student model) {
        final StudentDB returnValue = new StudentDB();
        returnValue.setId(model.getId());
        returnValue.setFirstName(model.getFirstName());
        returnValue.setLastName(model.getLastName());
        return returnValue;
    }

    private Student createFrom(StudentDB model) {
        final Student returnValue = new Student();
        returnValue.setId(model.getId());
        returnValue.setFirstName(model.getFirstName());
        returnValue.setLastName(model.getLastName());
        return returnValue;
    }
}