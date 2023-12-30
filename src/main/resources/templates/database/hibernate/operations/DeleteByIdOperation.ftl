public class ${name}DeleteByIdOperation extends AbstractDeleteByIdOperation<${name}DB> {
    public ${name}DeleteByIdOperation(EntityManagerFactory emf, long idToDelete) {
        super(emf, ${name}DB.class, idToDelete);
    }
}