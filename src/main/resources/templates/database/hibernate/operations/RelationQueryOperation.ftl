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

public class ${primaryName}${secondaryName}QueryBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Operation extends AbstractReadAllRelationsByPrimaryIdOperation<${primaryName}DB, ${secondaryName}DB, ${primaryName}${secondaryName}DB> {
    <#list query.queryParameters as queryParameter>
    private final ${queryParameter.type} ${queryParameter.name};
    </#list>

    public ${primaryName}${secondaryName}QueryBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Operation(EntityManagerFactory emf, long primaryId, <#list query.queryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter) {
        super(emf, ${primaryName}${secondaryName}DB.class, primaryId, searchParameter);
        <#list query.queryParameters as queryParameter>
        this.${queryParameter.name} = ${queryParameter.name};
        </#list>
    }

    @Override
    public List<Predicate> getAdditionalPredicates(CriteriaBuilder cb, From from) {
        <#list query.queryParameters as queryParameter>
        <#if queryParameter.comparisonType == "like">
        final Predicate match${queryParameter.name?cap_first} =  cb.like(from.get("${queryParameter.name}"), <#if queryParameter.pathParameter>"%" + this.${queryParameter.name} + "%"<#else>${queryParameter.defaultValue}</#if>);
        </#if>
        <#if queryParameter.comparisonType == "likeIgnoreCase">
        final Predicate match${queryParameter.name?cap_first} =  cb.like(cb.lower(from.get("${queryParameter.name}")), <#if queryParameter.pathParameter>"%" + this.${queryParameter.name}.toLowerCase() + "%"<#else>${queryParameter.defaultValue}</#if>);
        </#if>
        <#if queryParameter.comparisonType != "like" && queryParameter.comparisonType != "likeIgnoreCase">
        final Predicate match${queryParameter.name?cap_first} =  cb.${queryParameter.comparisonType}(from.get("${queryParameter.name}"), <#if queryParameter.pathParameter>this.${queryParameter.name}<#else>${queryParameter.defaultValue}</#if>);
        </#if>

        </#list>
        return List.of(<#list query.queryParameters as queryParameter>match${queryParameter.name?cap_first}<#sep>, </#list>);
    }
}
