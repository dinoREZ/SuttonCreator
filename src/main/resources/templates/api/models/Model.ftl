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

    public ${class} get${attributeName?cap_first}() {
        return ${attributeName};
    }

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