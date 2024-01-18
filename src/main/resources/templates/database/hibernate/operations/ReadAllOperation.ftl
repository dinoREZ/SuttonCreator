package ${basePackage}.server.database.hibernate.operations;

import ${basePackage}.server.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadAllOperation;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class ${name}ReadAllOperation extends AbstractReadAllOperation<${name}DB> {

    public ${name}ReadAllOperation(EntityManagerFactory emf, SearchParameter searchParameter) {
        super(emf, ${name}DB.class, searchParameter);
    }

    @Override
    public List<Predicate> getAdditionalPredicates(CriteriaBuilder cb, From from) {
        return null;
    }
}
