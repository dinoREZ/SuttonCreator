package ${basePackage}.database.hibernate;

import ${basePackage}.api.models.${name};
import ${basePackage}.database.${name}Dao;
import ${basePackage}.database.hibernate.dao.${name}DaoHibernate;
import ${basePackage}.database.hibernate.dao.${name}DaoHibernateImpl;
import ${basePackage}.database.hibernate.models.${name}DB;
import de.fhws.fiw.fds.sutton.server.database.searchParameter.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.sutton.server.database.results.SingleModelResult;

public class ${name}DaoAdapter implements ${name}Dao {
    private final ${name}DaoHibernate dao = new ${name}DaoHibernateImpl();

    @Override
    public NoContentResult create(${name} model) {
        final ${name}DB dbModel = createFrom(model);
        final NoContentResult returnValue = this.dao.create(dbModel);
        model.setId(dbModel.getId());
        return returnValue;
    }

    @Override
    public SingleModelResult<${name}> readById(long id) {
        SingleModelHibernateResult<${name}DB> result = this.dao.readById(id);
        if(result.isEmpty()) {
            return new SingleModelResult<>();
        }

        if(result.hasError()) {
            final SingleModelResult<${name}> returnValue = new SingleModelResult<>( );
            returnValue.setError( );
            return returnValue;
        }
        else {
            return new SingleModelResult<>(createFrom(result.getResult()));
        }
    }

    @Override
    public CollectionModelResult<${name}> readAll(SearchParameter searchParameter) {
        CollectionModelHibernateResult<${name}DB> result = dao.readAll(searchParameter);
        CollectionModelResult<${name}> returnValue;

        if(result.hasError()) {
            returnValue = new CollectionModelResult<>();
            returnValue.setError();
        } else {
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(this::createFrom).collect(Collectors.toList()));
        }

        return returnValue;
    }

    <#list queries as query>
    @Override
    public CollectionModelResult<${name}> readBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(<#list query.attributes as attributeTriple>${attributeTriple.left} ${attributeTriple.middle}, </#list>SearchParameter searchParameter) {
        CollectionModelHibernateResult<${name}DB> result = dao.readBy<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(<#list query.attributes as attributeTriple>${attributeTriple.middle}, </#list>searchParameter);

        CollectionModelResult<${name}> returnValue;
        if(result.hasError()) {
            returnValue = new CollectionModelResult<>();
            returnValue.setError();

        }
        else {
            returnValue = new CollectionModelResult<>(result.getResult().stream().map(this::createFrom).collect(Collectors.toList()));
        }
        return returnValue;
    }

    </#list>
    @Override
    public NoContentResult update(${name} model) {
        return dao.update(createFrom(model));
    }

    @Override
    public NoContentResult delete(long id) {
        return dao.delete(id);
    }

    private ${name}DB createFrom(${name} model) {
        final ${name}DB returnValue = new ${name}DB();
        returnValue.setId(model.getId());
        <#list attributes as attributeName, _>
        returnValue.set${attributeName?cap_first}(model.get${attributeName?cap_first}());
        </#list>
        return returnValue;
    }

    private ${name} createFrom(${name}DB model) {
        final ${name} returnValue = new ${name}();
        returnValue.setId(model.getId());
        <#list attributes as attributeName, _>
        returnValue.set${attributeName?cap_first}(model.get${attributeName?cap_first}());
        </#list>
        return returnValue;
    }
}