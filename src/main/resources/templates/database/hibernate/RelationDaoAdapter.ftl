package ${basePackage}.database.hibernate;

import ${basePackage}.api.models.${secondaryName};
import ${basePackage}.database.${primaryName}${secondaryName}Dao;
import ${basePackage}.database.hibernate.dao.*;
import ${basePackage}.database.hibernate.models.${secondaryName}DB;
import ${basePackage}.database.hibernate.models.${primaryName}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

import java.util.stream.Collectors;

public class ${primaryName}${secondaryName}DaoAdapter implements ${primaryName}${secondaryName}Dao {
    ${primaryName}${secondaryName}DaoHibernate dao = new ${primaryName}${secondaryName}DaoHibernateImpl();

    public ${primaryName}${secondaryName}DaoAdapter() {
        super();
    }

    <#list queries as query>
    @Override
    public CollectionModelResult<${secondaryName}> readBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(long primaryId, <#list query.attributes as attributeTriple>${attributeTriple.left} ${attributeTriple.middle}, </#list>SearchParameter searchParameter) {
        CollectionModelHibernateResult<${secondaryName}DB> result = this.dao.readBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(primaryId, <#list query.attributes as attributeTriple>${attributeTriple.middle}, </#list>searchParameter);

        CollectionModelResult<${secondaryName}> returnValue;
        if(result.hasError()) {
            returnValue = new CollectionModelResult<>();
            returnValue.setError();
        }
        else {
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(${secondaryName} -> createFrom(${secondaryName}, primaryId)).collect(Collectors.toList()));
        }
        return returnValue;
    }

    </#list>
    @Override
    public NoContentResult create(long primaryId, ${secondaryName} secondary) {
        ${secondaryName}DB ${secondaryName}DB = createFrom(secondary);
        NoContentResult returnValue = this.dao.create(primaryId, ${secondaryName}DB);
        secondary.setId(${secondaryName}DB.getId());
        return returnValue;
    }

    @Override
    public NoContentResult update(long primaryId, ${secondaryName} secondary) {
        ${secondaryName}DB ${secondaryName}DB = createFrom(secondary);
        NoContentResult returnValue = this.dao.update(primaryId, ${secondaryName}DB);
        secondary.setId(${secondaryName}DB.getId());
        return returnValue;
    }

    @Override
    public NoContentResult deleteRelation(long primaryId, long secondaryId) {
        return this.dao.deleteRelation(primaryId, secondaryId);
    }

    @Override
    public NoContentResult deleteRelationsFromPrimary(long primaryId) {
        return this.dao.deleteRelationsFromPrimary(primaryId);
    }

    @Override
    public NoContentResult deleteRelationsToSecondary(long secondaryId) {
        return this.dao.deleteRelationsToSecondary(secondaryId);
    }

    @Override
    public SingleModelResult<${secondaryName}> readById(long primaryId, long secondaryId) {
        SingleModelHibernateResult<${secondaryName}DB> result = this.dao.readById(primaryId, secondaryId);

        if(result.isEmpty()) {
            return new SingleModelResult<>();
        }

        if(result.hasError()) {
            SingleModelResult<${secondaryName}> returnValue = new SingleModelResult<>();
            returnValue.setError();
            return returnValue;
        }
        else {
            return new SingleModelResult<>(createFrom(result.getResult(), primaryId));
        }
    }

    @Override
    public CollectionModelResult<${secondaryName}> readAll(long primaryId, SearchParameter searchParameter) {
        CollectionModelHibernateResult<${secondaryName}DB> result = dao.readAll(primaryId, searchParameter);

        CollectionModelResult<${secondaryName}> returnValue;
        if(result.hasError()) {
            returnValue = new CollectionModelResult<>();
            returnValue.setError();
        }
        else {
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(course -> createFrom(course, primaryId)).collect(Collectors.toList()));
        }
        return returnValue;
    }

    private ${secondaryName} createFrom(${secondaryName}DB ${secondaryName?lower_case}DB, long primaryId) {
        final ${secondaryName} ${secondaryName?lower_case} = new ${secondaryName}();
        ${secondaryName?lower_case}.setPrimaryId(primaryId);
        ${secondaryName?lower_case}.setId(${secondaryName?lower_case}DB.getId());
        <#list secondaryAttributes as attributeName, _>
        ${secondaryName?lower_case}.set${attributeName?cap_first}(${secondaryName?lower_case}DB.get${attributeName?cap_first}());
        </#list>

        return ${secondaryName?lower_case};
    }

    private ${secondaryName}DB createFrom(${secondaryName} ${secondaryName?lower_case}) {
        final ${secondaryName}DB ${secondaryName?lower_case}DB = new ${secondaryName}DB();
        ${secondaryName?lower_case}DB.setId(${secondaryName?lower_case}.getId());
        <#list secondaryAttributes as attributeName, _>
        ${secondaryName?lower_case}DB.set${attributeName?cap_first}(${secondaryName?lower_case}.get${attributeName?cap_first}());
        </#list>

        return ${secondaryName?lower_case}DB;
    }
}
