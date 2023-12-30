@Entity
@Table(name = "${name}s")
public class ${name}DB extends AbstractDBModel {

<#list attributes as name, class>
@Column(name = "${name}")
private class name;

</#list>
public ${name}DB() {

}

<#list attributes as name, class>
public ${class} get${name?cap_first}() {
    return ${name};
}

public void set${name?cap_first}() {
    this.${name} = ${name};
}

</#list>
}