package ${basePackage}.server.database.hibernate.operations;

import ${basePackage}.server.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.server.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.models.SuttonColumnConstants;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.AbstractDatabaseOperation;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

public class ${secondaryName}sOf${primaryName}ByQueryOperation extends AbstractDatabaseOperation<${secondaryName}DB, CollectionModelHibernateResult<${secondaryName}DB>> {
    private final Class<${primaryName}${secondaryName}DB> classOfRelation = ${primaryName}${secondaryName}DB.class;
    private final long primaryId;
    <#list query.attributes as attributeName, class>
    private ${class} ${attributeName};
    </#list>
    private final SearchParameter searchParameter;

    public ${secondaryName}sOf${primaryName}ByQueryOperation(EntityManagerFactory emf, long primaryId, <#list query.attributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter) {
        super(emf);
        this.primaryId = primaryId;
        <#list query.attributes as attributeName, _>
        this.${attributeName} = ${attributeName};
        </#list>
        this.searchParameter = searchParameter;
    }

    @Override
    protected CollectionModelHibernateResult<${secondaryName}DB> run() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        CollectionModelHibernateResult<${secondaryName}DB> returnValue = new CollectionModelHibernateResult<>(readResults());
        returnValue.setTotalNumberOfResult(getTotalNumberOfResults());
        return returnValue;
    }

    @Override
    protected CollectionModelHibernateResult<${secondaryName}DB> errorResult() {
        final CollectionModelHibernateResult<${secondaryName}DB> returnValue = new CollectionModelHibernateResult<>();
        returnValue.setError();
        return returnValue;
    }

    private List<${secondaryName}DB> readResults() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<${primaryName}${secondaryName}DB> find = criteriaBuilder.createQuery(${primaryName}${secondaryName}DB.class);
        Root<${primaryName}${secondaryName}DB> root = find.from(${primaryName}${secondaryName}DB.class);
        Join<${primaryName}${secondaryName}DB, ${secondaryName}DB> join = root.join(SuttonColumnConstants.SECONDARY_MODEL);

        find.where(formulateConditions(criteriaBuilder, root, join));

        return this.em
                .createQuery(find)
                .setMaxResults(searchParameter.getSize())
                .setFirstResult(searchParameter.getOffset())
                .getResultList()
                .stream()
                .map(AbstractDBRelation::getSecondaryModel)
                .collect(Collectors.toList());
    }

    private int getTotalNumberOfResults() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> find = criteriaBuilder.createQuery(Long.class);
        Root<${primaryName}${secondaryName}DB> root = find.from(${primaryName}${secondaryName}DB.class);
        Join<${primaryName}${secondaryName}DB, ${secondaryName}DB> join = root.join(SuttonColumnConstants.SECONDARY_MODEL);

        find.select(criteriaBuilder.count(root)).where(formulateConditions(criteriaBuilder, root, join));

        return this.em
                .createQuery(find)
                .getSingleResult().intValue();
    }

    private Predicate formulateConditions(CriteriaBuilder criteriaBuilder, Root<${primaryName}${secondaryName}DB> root, Join<${primaryName}${secondaryName}DB, ${secondaryName}DB> join) {
        <#list query.attributes as attributeName, _>
        Predicate match${attributeName?cap_first} =  criteriaBuilder.like(join.get("${attributeName}"), "%" + this.${attributeName} + "%");
        </#list>
        Predicate primaryIdEquals = criteriaBuilder.equal(root.get(SuttonColumnConstants.DB_RELATION_ID).get(SuttonColumnConstants.PRIMARY_ID), this.primaryId);

        return criteriaBuilder.and(primaryIdEquals, <#list query.attributes as attributeName, _>match${attributeName?cap_first}<#sep>, </#list>);
    }
}
