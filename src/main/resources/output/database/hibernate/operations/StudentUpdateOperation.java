public class StudentUpdateOperation extends AbstractUpdateOperation<StudentDB> {
    public StudentUpdateOperation(EntityManagerFactory emf, StudentDB model) {
        super(emf, model);
    }
}