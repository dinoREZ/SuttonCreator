public interface StudentDaoHibernate extends IDatabaseAccessObjectHibernate<StudentDB> {

    CollectionModelHibernateResult<Student> readByQuery(String firstName, String lastName, SearchParameter searchParameter);

}