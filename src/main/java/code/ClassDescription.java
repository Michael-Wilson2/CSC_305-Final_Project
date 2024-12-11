package code;

import java.util.ArrayList;
import java.util.List;

// TODO: make FactoryDescription and/or ______Description child classes?

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

  private List<String> methods = new ArrayList<>();
  // ---------------------------------------------------------------


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

  public void addMethod(String methodName) {
    methods.add(methodName);
  }

  public List<String> getMethods() {
    return methods;
  }
}
