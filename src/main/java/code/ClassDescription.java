package code;

import java.util.ArrayList;
import java.util.List;


/** A class to store all the parts of the code so that a code "skeleton" is easy to build
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class ClassDescription {
  public static final String PUBLIC = "public";
  public static final String PRIVATE = "private";

  public static final String CLASS = "class";
  public static final String ABSTRACT_CLASS = "abstract class";
  public static final String INTERFACE = "interface";

  // ---------------------------------------------------------------
  private List<String> imports = new ArrayList<>();

  private String type = null;
  private String name = null;
  private List<String> implementations = new ArrayList<>();
  private String extension = null; // single inheritance; can only extend one class

  private List<String> variables = new ArrayList<>();

  private String constructorAccess = PUBLIC;
  private String constructorBody = "";

  private List<String> methods = new ArrayList<>();
  // ---------------------------------------------------------------

  public void setImports(List<String> importList) {
    this.imports = importList;
  }

  public void addImport(String importName) {
    imports.add(importName);
  }

  public List<String> getImports() {
    return imports;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setImplementations(List<String> implementationList) {
    this.implementations = implementationList;
  }

  public void addImplementation(String interfaceName) {
    implementations.add(interfaceName);
  }

  public List<String> getImplementations() {
    return implementations;
  }

  public void setExtension(String parentName) {
    extension = parentName;
  }

  public String getExtension() {
    return extension;
  }

  public void setVariables(List<String> variableList) {
    this.variables = variableList;
  }

  public void addVariable(String varDeclaration) {
    variables.add(varDeclaration);
  }

  public List<String> getVariables() {
    return variables;
  }

  public void setConstructorAccess(String constructorAccess) {
    this.constructorAccess = constructorAccess;
  }

  public String getConstructorAccess() {
    return constructorAccess;
  }

  public void setConstructorBody(String code) {
    this.constructorBody = code;
  }

  public String getConstructorBody() {
    return this.constructorBody;
  }

  public void setMethods(List<String> methodList) {
    this.methods = methodList;
  }

  public void addMethod(String methodDefinition) {
    methods.add(methodDefinition);
  }

  public List<String> getMethods() {
    return methods;
  }
}
