public interface ${name}Dao extends IDatabaseAccessObject<${name}> {
    <#if hasQuery>CollectionModelResult<${name}> readByQuery(<#list queryAttributes as name, class>${class} ${name}, </#list>SearchParameter searchParameter);</#if>
}