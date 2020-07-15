package ${packageName};

import javax.persistence.*;

/**
* @author Marcel Berger - 4BrainSolutions GmbH
*/
@Entity
@Table(name = "${capitalClassName}")
@NamedQueries({
@NamedQuery(name = ${className}Entity.FIND_ALL, query = "SELECT v FROM ${className}Entity v"),
})
public class ${className}Entity {

public static final String FIND_ALL = "${className}Entity.find_all";

<#list properties as p>
    @Column(name = "${p.propertyNameOriginal}")
    private ${p.propertyType} ${p.propertyName};

</#list>
<#list properties as p>

    public ${p.propertyType} get${p.propertyMethodName}() {
    return this.${p.propertyName};
    }

    public void set${p.propertyMethodName}(${p.propertyType} ${p.propertyName}) {
    this.${p.propertyName} = ${p.propertyName};
    }
</#list>
}