public class StudentReadByIdOperation extends AbstractReadByIdOperation<StudentDB> {
    public StudentReadByIdOperation(EntityManagerFactory emf, long id) {
        super(emf, StudentDB.class, id);
    }
}