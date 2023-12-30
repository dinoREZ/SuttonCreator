public interface StudentDao extends IDatabaseAccessObject<Student> {
    CollectionModelResult<Student> readByQuery(String firstName, String lastName, SearchParameter searchParameter);
}