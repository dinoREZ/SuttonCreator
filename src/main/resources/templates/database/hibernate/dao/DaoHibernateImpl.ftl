public class ${name}DaoHibernateImpl implements ${name}DaoHibernate {
    private static final EntityManagerFactory emf = IDatabaseConnection.SUTTON_EMF;

    public ${name}DaoHibernateImpl() {
        super();
    }

<#if hasQuery>
    @Override
    public CollectionModelHibernateResult<${name}DB> readByQuery(<#list queryAttributes as name, class>${class} ${name}, </#list>SearchParameter searchParameter) {
        return new ${name}QueryOperation(emf, <#list queryAttributes as name, _>${name}, </#list>searchParameter).start();
    }
</#if>

    @Override
    public NoContentResult create(${name}DB model) {
        return new ${name}PersistOperation(emf, model).start();
    }

    @Override
    public SingleModelHibernateResult<${name}DB> readById(long id) {
        return new ${name}ReadByIdOperation(emf, id).start();
    }

    @Override
    public CollectionModelHibernateResult<${name}DB> readAll(SearchParameter searchParameter) {
        // TODO?
        return null;
    }

    @Override
    public NoContentResult update(${name}DB model) {
        return new ${name}UpdateOperation(emf, model).start();
    }

    @Override
    public NoContentResult delete(long id) {
        return new ${name}DeleteByIdOperation(emf, id).start();
    }
}