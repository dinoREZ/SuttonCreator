public class StudentPersistOperation extends AbstractPersistOperation<StudentDB> {
    public StudentPersistOperation(EntityManagerFactory emf, StudentDB model) {
        super(emf, model);
    }
}