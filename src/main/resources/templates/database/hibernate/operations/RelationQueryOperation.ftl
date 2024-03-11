package ${basePackage}.database.hibernate.operations;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractReadAllRelationsByPrimaryIdOperation;
import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}DB;
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

public class ${primaryName}${secondaryName}QueryBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>Operation extends AbstractReadAllRelationsByPrimaryIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    private String name;
    private String room;

    public ${primaryName}${secondaryName}QueryBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>Operation(EntityManagerFactory emf, long primaryId, <#list query.attributes as attributeTriple>${attributeTriple.left} ${attributeTriple.middle}, </#list>SearchParameter searchParameter) {
        super(emf, ${primaryName}${secondaryName}DB.class, primaryId, searchParameter);
        this.name = name;
        this.room = room;
        this.searchParameter = searchParameter;
    }

    @Override
    public List<Predicate> getAdditionalPredicates(CriteriaBuilder cb, From from) {
        <#list query.attributes as attributeTriple>
        final Predicate match${attributeTriple.middle?cap_first} =  cb.like(from.get("${attributeTriple.middle}"), "%" + this.${attributeTriple.middle} + "%");
        </#list>
        return List.of(<#list query.attributes as attributeTriple>match${attributeTriple.middle?cap_first}<#sep>, </#list>);
    }
}
