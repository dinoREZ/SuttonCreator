public interface ${name}DaoHibernate extends IDatabaseAccessObjectHibernate<${name}DB> {

    <#if hasQuery>CollectionModelHibernateResult<${name}> readByQuery(<#list queryAttributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter);</#if>

}