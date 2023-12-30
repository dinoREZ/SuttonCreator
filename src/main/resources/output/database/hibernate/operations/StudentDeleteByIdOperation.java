public class StudentDeleteByIdOperation extends AbstractDeleteByIdOperation<StudentDB> {
    public StudentDeleteByIdOperation(EntityManagerFactory emf, long idToDelete) {
        super(emf, StudentDB.class, idToDelete);
    }
}