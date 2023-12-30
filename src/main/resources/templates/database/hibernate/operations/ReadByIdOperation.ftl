public class ${name}ReadByIdOperation extends AbstractReadByIdOperation<${name}DB> {
    public ${name}ReadByIdOperation(EntityManagerFactory emf, long id) {
        super(emf, ${name}DB.class, id);
    }
}