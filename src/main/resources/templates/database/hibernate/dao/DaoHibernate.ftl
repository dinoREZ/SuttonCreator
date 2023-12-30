public interface ${name}DaoHibernate extends IDatabaseAccessObjectHibernate<${name}DB> {

    <#if hasQuery>CollectionModelHibernateResult<${name}> readByQuery(<#list queryAttributes as name, class>${class} ${name}, </#list>SearchParameter searchParameter);</#if>

}