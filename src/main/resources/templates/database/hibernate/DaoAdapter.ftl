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
        // TODO
        return null;
    }

    <#if hasQuery>
    @Override
    public CollectionModelResult<${name}> readByQuery(<#list queryAttributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter) {
        CollectionModelHibernateResult<${name}DB> result = dao.readByQuery(<#list queryAttributes as attributeName, _>${attributeName}, </#list>searchParameter);

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

    </#if>
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