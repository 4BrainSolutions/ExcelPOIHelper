package ${packageName};

/**
* @author Marcel Berger - 4BrainSolutions GmbH
*/
public class ${className} {

<#list properties as p>
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