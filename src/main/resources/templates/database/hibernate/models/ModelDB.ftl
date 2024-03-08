package ${basePackage}.database.hibernate.models;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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

    public void set${attributeName?cap_first}(${class} ${attributeName}) {
        this.${attributeName} = ${attributeName};
    }

</#list>
}