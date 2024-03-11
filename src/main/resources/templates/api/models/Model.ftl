package ${basePackage}.api.models;

import com.owlike.genson.annotation.JsonConverter;
import de.fhws.fiw.fds.sutton.server.api.converter.JsonServerLinkConverter;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import de.fhws.fiw.fds.sutton.server.utils.JsonDateTimeConverter;
import org.glassfish.jersey.linking.InjectLink;

import javax.ws.rs.core.Link;

public class ${name} extends AbstractModel {

<#--Attributes-->
<#list attributes as attributeName, class>
    private ${class} ${attributeName};
</#list>

<#list links as linkName, link>
    @InjectLink(
    style = InjectLink.Style.${link.style},
    value = "${link.value}",
    rel = "${link.relation}",
    title = "${link.title}",
    condition = "${link.condition}"
    )
    private Link ${linkName};

</#list>
<#--Constructor-->
    public ${name}() {
    }
<#--Getters and Setters-->
<#list attributes as attributeName, class>

    <#if class == "java.time.LocalDate">@JsonConverter(JsonDateTimeConverter.class)</#if>
    public ${class} get${attributeName?cap_first}() {
        return ${attributeName};
    }

    <#if class == "java.time.LocalDate">@JsonConverter(JsonDateTimeConverter.class)</#if>
    public void set${attributeName?cap_first}(${class} ${attributeName}) {
        this.${attributeName} = ${attributeName};
    }
</#list>
<#list links as linkName, _>

    @JsonConverter(JsonServerLinkConverter.class)
    public Link get${linkName?cap_first}() {
        return ${linkName};
    }

    public void set${linkName?cap_first}(Link ${linkName}) {
        this.${linkName} = ${linkName};
    }
</#list>
}