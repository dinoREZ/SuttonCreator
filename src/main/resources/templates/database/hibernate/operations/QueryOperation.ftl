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

    <#list query.queryParameters as queryParameter>
    private final ${queryParameter.type} ${queryParameter.name};
    </#list>

    public ${name}QueryBy<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Operation(EntityManagerFactory emf,<#list query.queryParameters as queryParameter>${queryParameter.type} ${queryParameter.name}, </#list>SearchParameter searchParameter) {
        super(emf, ${name}DB.class, searchParameter);
        <#list query.queryParameters as queryParameter>
        this.${queryParameter.name} = ${queryParameter.name};
        </#list>
    }


    @Override
    public List<Predicate> getAdditionalPredicates(CriteriaBuilder cb, From from) {
        <#list query.queryParameters as queryParameter>
        final Predicate match${queryParameter.name?cap_first} =  cb.like(from.get("${queryParameter.name}"), "%" + this.${queryParameter.name} + "%");
        </#list>
        return List.of(<#list query.queryParameters as queryParameter>match${queryParameter.name?cap_first}<#sep>, </#list>);
    }
}
