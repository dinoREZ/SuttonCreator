public interface ${name}Dao extends IDatabaseAccessObject<${name}> {
    <#if hasQuery>CollectionModelResult<${name}> readByQuery(<#list queryAttributes as attributeName, class>${class} ${attributeName}, </#list>SearchParameter searchParameter);</#if>
}