package ${basePackage}.database.hibernate.operations;

import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractReadAllRelationsByPrimaryIdOperation;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class ${primaryName}${secondaryName}ReadAllOperation extends AbstractReadAllRelationsByPrimaryIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {

    public ${primaryName}${secondaryName}ReadAllOperation(EntityManagerFactory emf, long primaryId, SearchParameter searchParameter) {
        super(emf, ${primaryName}${secondaryName}DB.class, primaryId, searchParameter);
    }

    @Override
    public List<Predicate> getAdditionalPredicates(CriteriaBuilder cb, From from) {
        return null;
    }
}
