package ${basePackage}.database;

<#list resources as resource>
<#if usesInMemory>
import ${basePackage}.database.inmemory.${resource.name}DaoImpl;
<#else>
import ${basePackage}.database.hibernate.${resource.name}DaoAdapter;
</#if>
<#list resource.subResources as subResource>
<#if usesInMemory>
import ${basePackage}.database.inmemory.${subResource.name}sOf${resource.name}DaoImpl;
<#else>
import ${basePackage}.database.hibernate.${subResource.name}sOf${resource.name}DaoAdapter;
</#if>
</#list>
</#list>

public class DaoFactory {

    private static DaoFactory INSTANCE;

    public static DaoFactory getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DaoFactory();
        }

        return INSTANCE;
    }

    <#list resources as resource>
    private final ${resource.name}Dao ${resource.name?lower_case}Dao;
    <#list resource.subResources as subResource>
    private final ${subResource.name}Of${resource.name}Dao ${subResource.name?lower_case}Of${resource.name}Dao;
    </#list>
    </#list>

    private DaoFactory() {
        <#list resources as resource>
        this.${resource.name?lower_case}Dao = new ${resource.name}Dao<#if usesInMemory>Impl<#else>Adapter</#if>();
        <#list resource.subResources as subResource>
        this.${subResource.name?lower_case}sOf${resource.name} = new ${subResource.name}sOf${resource.name}Dao<#if usesInMemory>Impl<#else>Adapter</#if>();
        </#list>
        </#list>
    }

    <#list resources as resource>
    public ${resource.name}Dao get${resource.name}Dao() {
        return this.${resource.name?lower_case}Dao;
    }

    <#list resource.subResources as subResource>
    public ${subResource.name}sOf${resource.name}Dao get${subResource.name}sOf${resource.name}Dao() {
        return ${subResource.name?lower_case}sOf${resource.name}Dao;
    }

    </#list>
    </#list>
}
