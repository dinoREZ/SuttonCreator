public class ${name} extends AbstractModel {

<#--Attributes-->
<#list attributes as name, class>
    private ${class} ${name};
</#list>

<#list links as name, link>
    @InjectLink(
    style = InjectLink.Style.${link.style},
    value = "${link.value}",
    rel = "${link.relation}",
    title = "${link.title}",
    condition = "${link.condition}"
    )
    private Link ${name};

</#list>
<#--Constructor-->
    public ${name}() {
    }
<#--Getters and Setters-->
<#list attributes as name, class>

    public ${class} get${name?cap_first}() {
        return ${name};
    }

    public void set${name?cap_first}(${class} ${name}) {
        this.${name} = ${name};
    }
</#list>
<#list links as name, _>

    @JsonConverter(JsonServerLinkConverter.class)
    public Link get${name?cap_first}() {
        return ${name};
    }

    public void set${name?cap_first}(Link ${name}) {
        this.${name} = ${name};
    }
</#list>
}