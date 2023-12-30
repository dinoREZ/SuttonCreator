public class ${name}ByQueryOperation extends AbstractDatabaseOperation<${name}DB, CollectionModelHibernateResult<${name}DB>> {

    <#list queryAttributes as name, class>
    private ${class} ${name};
    </#list>
    private SearchParameter searchParameter;

    public ${name}ByQueryOperation(EntityManagerFactory emf, <#list queryAttributes as name, class>${class} ${name}, </#list>SearchParameter searchParameter) {
        super(emf);
        <#list queryAttributes as name, _>
        this.${name} = ${name};
        </#list>
        this.searchParameter = searchParameter;
    }

    private Predicate formulateConditions(CriteriaBuilder criteriaBuilder, Root<${name}DB> root) {
        <#list queryAttributes as name, _>
        final Predicate match${name?cap_first} =  criteriaBuilder.like(root.get("${name}"), "%" + this.${name} + "%");
        </#list>

        return criteriaBuilder.and(<#list queryAttributes as name, _>match${name?cap_first}<#sep>, </#list>);
    }

    @Override
    protected CollectionModelHibernateResult<${name}DB> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CollectionModelHibernateResult<${name}DB> returnValue = new CollectionModelHibernateResult<>(readResult());
        returnValue.setTotalNumberOfResult(getTotalResultCount());

        return returnValue;
    }

    private List<${name}DB> readResult() {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<${name}DB> criteriaQuery = criteriaBuilder.createQuery(${name}DB.class);
        final Root<${name}DB> root = criteriaQuery.from(${name}DB.class);

        criteriaQuery = criteriaQuery.select(root).where(formulateConditions(criteriaBuilder, root));

        return em.createQuery(criteriaQuery)
                .setFirstResult(searchParameter.getOffset())
                .setMaxResults(searchParameter.getSize())
                .getResultList();
    }

    private int getTotalResultCount() {
        final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        final CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        final Root<${name}DB> root = countQuery.from(${name}DB.class);

        countQuery.select(criteriaBuilder.count(root)).where(formulateConditions(criteriaBuilder, root));
        return em
                .createQuery(countQuery)
                .getSingleResult()
                .intValue();
    }

    @Override
    protected CollectionModelHibernateResult<${name}DB> errorResult() {
        final CollectionModelHibernateResult<${name}DB> returnValue = new CollectionModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }
}