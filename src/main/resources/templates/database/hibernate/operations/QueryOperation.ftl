package ${basePackage}.database.hibernate.operations;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadAllOperation;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import ${basePackage}.database.hibernate.models.${name}DB;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class ${name}QueryBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Operation extends AbstractReadAllOperation<${name}DB> {

    <#list query.pathQueryParameters as queryParameter>
    private final ${queryParameter.type} ${queryParameter.name};
    </#list>

    public ${name}QueryBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Operation(EntityManagerFactory emf,<#list query.pathQueryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter) {
        super(emf, ${name}DB.class, searchParameter);
        <#list query.pathQueryParameters as queryParameter>
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
