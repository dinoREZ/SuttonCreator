package ${basePackage}.api.services;

import ${basePackage}.api.models.${resource.name};
<#list resource.subResources as subResource>
import ${basePackage}.api.models.${subResource.name};
</#list>
<#list resource.queries as query>
import ${basePackage}.api.queries.${resource.name}By<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>Query;
</#list>
<#list resource.subResources as subResource>
<#list subResource.queries as query>
import ${basePackage}.api.queries.${resource.name}${subResource.name}By<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>Query;
</#list>
</#list>
import ${basePackage}.api.rateLimiting.AnyApiKeyRateLimiter;
import ${basePackage}.api.states.*;
import de.fhws.fiw.fds.sutton.server.api.services.AbstractService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("${resource.pathElement}")
public class ${resource.name}Service extends AbstractService {

    <#if resource.isDefaultGetNeeded()>
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll${resource.name}() {
        return new Get${resource.name}CollectionState.Builder()
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    </#if>
    <#list resource.queries as query>
    @GET
    @Path("${query.subPathElement}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get${resource.name}By<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(<#list query.attributes as attributeTriple>@DefaultValue("${attributeTriple.right}") @QueryParam("${attributeTriple.middle}") ${attributeTriple.left} ${attributeTriple.middle}<#sep>, </#list>) {
        ${resource.name}By<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>Query query = new ${resource.name}By<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>Query(<#list query.attributes as attributeTriple>${attributeTriple.middle}<#sep>, </#list>);
        return new Get${resource.name}CollectionState.Builder()
                .setQuery(query)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setContext(this.context)
                .setHttpServletRequest(this.httpServletRequest)
                .setRateLimiter(new AnyApiKeyRateLimiter())
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
                .setRateLimiter(new AnyApiKeyRateLimiter())
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
                .setRateLimiter(new AnyApiKeyRateLimiter())
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
                .setRateLimiter(new AnyApiKeyRateLimiter())
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
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    <#list resource.subResources as subResource>
    <#if subResource.isDefaultGetNeeded()>
    @GET
    @Path("{primaryId : \\d+}/${subResource.pathElement}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get${resource.name}${subResource.name}Collection(@PathParam("primaryId") final long primaryId) {
        return new Get${resource.name}${subResource.name}CollectionState.Builder()
                .setParentId(primaryId)
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }

    </#if>
    <#list subResource.queries as query>
    @GET
    @Path("${query.subPathElement}")
    @Path("{primaryId : \\d+}/${subResource.pathElement}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get${resource.name}${subResource.name}By<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>(@PathParam("primaryId") final long primaryId,
                                       <#list query.attributes as attributeTriple>@DefaultValue("${attributeTriple.right}") @QueryParam("${attributeTriple.middle}") ${attributeTriple.left} ${attributeTriple.middle}<#sep>, </#list>) {
        return new Get${resource.name}${subResource.name}CollectionState.Builder()
                .setParentId(primaryId)
                .setQuery(new ${resource.name}${subResource.name}By<#list query.attributes as attributeTriple>${attributeTriple.middle?cap_first}</#list>Query(<#list query.attributes as attributeTriple>${attributeTriple.middle}<#sep>, </#list>))
                .setUriInfo(this.uriInfo)
                .setRequest(this.request)
                .setHttpServletRequest(this.httpServletRequest)
                .setContext(this.context)
                .setRateLimiter(new AnyApiKeyRateLimiter())
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
                .setRateLimiter(new AnyApiKeyRateLimiter())
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
                .setRateLimiter(new AnyApiKeyRateLimiter())
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
                .setRateLimiter(new AnyApiKeyRateLimiter())
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
                .setRateLimiter(new AnyApiKeyRateLimiter())
                .setAuthProvider(this.authProvider)
                .build()
                .execute();
    }
    </#list>
}
