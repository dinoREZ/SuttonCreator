@Entity
@Table(name = "${name}s")
public class ${name}DB extends AbstractDBModel {

<#list attributes as attributeName, class>
    @Column(name = "${attributeName}")
    private ${class} ${attributeName};

</#list>
    public ${name}DB() {

    }

<#list attributes as attributeName, class>
    public ${class} get${attributeName?cap_first}() {
        return ${attributeName};
    }

    public void set${attributeName?cap_first}() {
        this.${attributeName} = ${attributeName};
    }

</#list>
}