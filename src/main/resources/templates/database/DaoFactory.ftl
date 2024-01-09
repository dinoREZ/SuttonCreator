package ${basePackage}.server.database;

<#list resources as resource>
<#if usesInMemory>
import ${basePackage}.server.database.inmemory.${resource}DaoImpl;
<#else>
import ${basePackage}.server.database.hibernate.${resource}DaoAdapter;
</#if>
</#list>
<#list subResources as resource, subResource>
<#if usesInMemory>
import ${basePackage}.server.database.inmemory.${subResource}sOf${resource}DaoImpl;
<#else>
import ${basePackage}.server.database.hibernate.${subResource}sOf${resource}DaoAdapter;
</#if>
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
    private final ${resource}Dao ${resource?lower_case}Dao;
    </#list>

    <#list subResources as resource, subResource>
    private final ${subResource}Of${resource}Dao ${subResource?lower_case}Of${resource}Dao;
    </#list>

    private DaoFactory() {
        <#list resources as resource>
        this.${resource?lower_case}Dao = new ${resource}Dao<#if usesInMemory>Impl<#else>Adapter</#if>();
        </#list>
        <#list subResources as resource, subResource>
        this.${subResource?lower_case}sOf${resource} = new ${subResource}sOf${resource}Dao<#if usesInMemory>Impl<#else>Adapter</#if>();
        </#list>
    }

    <#list resources as resource>
    public ${resource}Dao get${resource}Dao() {
        return this.${resource?lower_case}Dao;
    }
    </#list>

    <#list subResources as resource, subResource>
    public ${subResource}sOf${resource}Dao get${subResource}sOf${resource}Dao() {
        return ${subResource?lower_case}sOf${resource}Dao;
    }
    </#list>
}
