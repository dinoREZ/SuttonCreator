package ${basePackage}.database.hibernate.operations;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadAllOperation;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import ${basePackage}.database.hibernate.models.${name}DB;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

import java.util.List;

public class ${name}QueryBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>Operation extends AbstractReadAllOperation<${name}DB> {

    <#list query.attributes as attributeTriple>
    private final ${attributeTriple.left} ${attributeTriple.middle};
    </#list>

    public ${name}QueryBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>Operation(EntityManagerFactory emf,<#list query.attributes as attributeTriple>${attributeTriple.left} ${attributeTriple.middle}, </#list>SearchParameter searchParameter) {
        super(emf, ${name}DB.class, searchParameter);
        <#list query.attributes as attributeTriple>
        this.${attributeTriple.middle} = ${attributeTriple.middle};
        </#list>
    }


    @Override
    public List<Predicate> getAdditionalPredicates(CriteriaBuilder cb, From from) {
        <#list query.attributes as attributeTriple>
        final Predicate match${attributeTriple.middle?cap_first} =  cb.like(from.get("${attributeTriple.middle}"), "%" + this.${attributeTriple.middle} + "%");
        </#list>
        return List.of(<#list query.attributes as attributeTriple>match${attributeTriple.middle?cap_first}<#sep>, </#list>);
    }
}
