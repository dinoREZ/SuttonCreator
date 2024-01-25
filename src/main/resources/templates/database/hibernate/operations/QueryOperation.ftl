package ${basePackage}.server.database.hibernate.operations;

import d${basePackage}.server.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ${name}ByQueryOperation extends AbstractDatabaseOperation<${name}DB, CollectionModelHibernateResult<${name}DB>> {

    <#list query.attributes as attributeName, class>
    private ${class} ${attributeName};
    </#list>
    private SearchParameter searchParameter;

    public ${name}ByQueryOperation(EntityManagerFactory emf, <#list query.attributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter) {
        super(emf);
        <#list query.attributes as attributeName, _>
        this.${attributeName} = ${attributeName};
        </#list>
        this.searchParameter = searchParameter;
    }

    private Predicate formulateConditions(CriteriaBuilder criteriaBuilder, Root<${name}DB> root) {
        <#list query.attributes as attributeName, _>
        final Predicate match${attributeName?cap_first} =  criteriaBuilder.like(root.get("${attributeName}"), "%" + this.${attributeName} + "%");
        </#list>

        return criteriaBuilder.and(<#list query.attributes as attributeName, _>match${attributeName?cap_first}<#sep>, </#list>);
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