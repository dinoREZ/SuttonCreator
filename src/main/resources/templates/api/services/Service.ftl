package ${basePackage}.api.services;

import ${basePackage}.api.models.${resource.name};
<#list resource.subResources as subResource>
import ${basePackage}.api.models.${subResource.name};
</#list>
import ${basePackage}.api.queries.*;
import ${basePackage}.api.rateLimiting.AnyApiKeyRateLimiter;
import ${basePackage}.api.states.*;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("${resource.pathElement}")
public class ${resource.name}Service extends AbstractService {

    <#if resource.isDefaultGetNeeded()>
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll${resource.name}(@DefaultValue("${resource.getDefaultSorting()}") @QueryParam("orderBy") String orderBy<#if resource.usePaging>, @DefaultValue("${resource.defaultPagingOffset}") @QueryParam("offset") int offset, @DefaultValue("${resource.defaultPagingSize}") @QueryParam("size") int size</#if>) {
        return new Get${resource.name}CollectionState.Builder()
                .setQuery(new ${resource.name}ReadAllQuery().setOrderByAttributes(orderBy)<#if resource.usePaging>.setPagingBehavior(new PagingBehaviorUsingOffsetSize<${resource.name}>(offset, size))</#if>)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    </#if>
    <#list resource.queries as query>
    @GET<#if query.subPathElement != "">
    @Path("${query.subPathElement}")</#if>
    @Produces(MediaType.APPLICATION_JSON)
    public Response get${resource.name}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(<#list query.pathQueryParameters as queryParameter>@DefaultValue(${queryParameter.defaultValue}) @QueryParam("${queryParameter.name}") ${queryParameter.type} ${queryParameter.name}, </#list>@DefaultValue("${resource.getDefaultSorting()}") @QueryParam("orderBy") String orderBy<#if resource.usePaging>, @DefaultValue("${resource.defaultPagingOffset}") @QueryParam("offset") int offset, @DefaultValue("${resource.defaultPagingSize}") @QueryParam("size") int size</#if>) {
        return new Get${resource.name}CollectionState.Builder()
                .setQuery(new ${resource.name}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Query(<#list query.pathQueryParameters as queryParameter>${queryParameter.name}<#sep>, </#list>).setOrderByAttributes(orderBy)<#if resource.usePaging>.setPagingBehavior(new PagingBehaviorUsingOffsetSize<${resource.name}>(offset, size))</#if>)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }
    </#list>

    @GET
    @Path("{id : \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get${resource.name}(@PathParam("id") final long id) {
        return new Get${resource.name}State.Builder()
                .setRequestedId(id)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create${resource.name}(final ${resource.name} model) {
        return new Post${resource.name}State.Builder()
                .setModelToCreate(model)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    @PUT
    @Path("{id : \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update${resource.name}(@PathParam("id") long id, final ${resource.name} model) {
        return new Put${resource.name}State.Builder()
                .setModelToUpdate(model)
                .setRequestedId(id)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    @DELETE
    @Path("{id : \\d+}")
    public Response delete${resource.name}(@PathParam("id") final long id) {
        return new Delete${resource.name}State.Builder()
                .setRequestedId(id)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    <#list resource.subResources as subResource>
    <#if subResource.isDefaultGetNeeded()>
    @GET
    @Path("{primaryId : \\d+}/${subResource.pathElement}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get${resource.name}${subResource.name}Collection(@PathParam("primaryId") final long primaryId, @DefaultValue("false") @QueryParam("showAll") boolean showAll, @DefaultValue("${resource.getDefaultSorting()}") @QueryParam("orderBy") String orderBy<#if resource.usePaging>, @DefaultValue("${resource.defaultPagingOffset}") @QueryParam("offset") int offset, @DefaultValue("${resource.defaultPagingSize}") @QueryParam("size") int size</#if>) {
        return new Get${resource.name}${subResource.name}CollectionState.Builder()
                .setQuery(new ${resource.name}${subResource.name}ReadAllQuery(primaryId, showAll).setOrderByAttributes(orderBy)<#if resource.usePaging>.setPagingBehavior(new PagingBehaviorUsingOffsetSize<${resource.name}>(offset, size))</#if>)
                .setParentId(primaryId)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    </#if>
    <#list subResource.queries as query>
    @GET<#if query.subPathElement != "">
    @Path("{primaryId : \\d+}/${subResource.pathElement}/${query.subPathElement}")
    <#else>

    @Path("{primaryId : \\d+}/${subResource.pathElement}")
    </#if>
    @Produces(MediaType.APPLICATION_JSON)
    public Response get${resource.name}${subResource.name}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>(@PathParam("primaryId") final long primaryId, @DefaultValue("false") @QueryParam("showAll") boolean showAll,
                                       <#list query.pathQueryParameters as queryParameter>@DefaultValue(${queryParameter.defaultValue}) @QueryParam("${queryParameter.name}") ${queryParameter.type} ${queryParameter.name}, </#list>@DefaultValue("${resource.getDefaultSorting()}") @QueryParam("orderBy") String orderBy<#if resource.usePaging>, @DefaultValue("${resource.defaultPagingOffset}") @QueryParam("offset") int offset, @DefaultValue("${resource.defaultPagingSize}") @QueryParam("size") int size</#if>) {
        return new Get${resource.name}${subResource.name}CollectionState.Builder()
                .setParentId(primaryId)
                .setQuery(new ${resource.name}${subResource.name}By<#list query.queryParameters as queryParameter>${queryParameter.name?cap_first}</#list>Query(primaryId, showAll, <#list query.pathQueryParameters as queryParameter>${queryParameter.name}<#sep>, </#list>).setOrderByAttributes(orderBy)<#if resource.usePaging>.setPagingBehavior(new PagingBehaviorUsingOffsetSize<${resource.name}>(offset, size))</#if>)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    </#list>
    @GET
    @Path("{primaryId: \\d+}/${subResource.pathElement}/{secondaryId: \\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get${resource.name}${subResource.name}ById(@PathParam("primaryId") final long primaryId,
                                           @PathParam("secondaryId") final long secondaryId) {
        return new Get${resource.name}${subResource.name}State.Builder()
                .setParentId(primaryId)
                .setRequestedId(secondaryId)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    @POST
    @Path("{primaryId: \\d+}/${subResource.pathElement}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create${resource.name}${subResource.name}(@PathParam("primaryId") final long primaryId, final ${subResource.name} model) {
        return new Post${resource.name}${subResource.name}State.Builder()
                .setParentId(primaryId)
                .setModelToCreate(model)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    @PUT
    @Path("{primaryId: \\d+}/${subResource.pathElement}/{secondaryId: \\d+}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update${resource.name}${subResource.name}(@PathParam("primaryId") final long primaryId,
                                          @PathParam("secondaryId") final long secondaryId, final ${subResource.name} model) {
        return new Put${resource.name}${subResource.name}State.Builder()
                .setParentId(primaryId)
                .setRequestedId(secondaryId)
                .setModelToUpdate(model)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    @DELETE
    @Path("{primaryId: \\d+}/${subResource.pathElement}/{secondaryId: \\d+}")
    public Response delete${resource.name}${subResource.name}(@PathParam("primaryId") final long primaryId,
                                          @PathParam("secondaryId") final long secondaryId) {
        return new Delete${resource.name}${subResource.name}State.Builder()
                .setParentId(primaryId)
                .setRequestedId(secondaryId)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(AnyApiKeyRateLimiter.anyApiKeyRateLimiter)
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }
    </#list>
}
