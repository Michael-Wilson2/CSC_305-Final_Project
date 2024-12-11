package code;

import diagram.*;

public class CodeCreator {
  // need to write code for each box in the diagram

  /*
   * public <class / interface> <box name>
   * implements ...
   * extends ___
   * {
   *
   * // variables
   * int var1;
   *
   * // constructor
   * <public / private> <box name>() {
   * // do super(new Object()) for PCS, otherwise leave blank
   * }
   *
   * // methods...
   * }
   */

  public String createCode(DiagramElement element) {
    ClassDescription description = getClassDescription((Box) element); // use before refactoring decorator

    // TODO: should just be able to return this line instead using decorator pattern
//    ClassDescription description = element.updateDescription(new ClassDescription());

    StringBuilder codeBuilder = new StringBuilder(); // TODO: can chain methods?
    codeBuilder.append(String.format("** imports **%n"));
    addImports(codeBuilder, description);

    codeBuilder.append(String.format("** header **%n"));
    addHeader(codeBuilder, description);

    codeBuilder.append(String.format("** variables **%n"));
    addVariables(codeBuilder, description);

    codeBuilder.append(String.format("** constructor **%n"));
    addConstructor(codeBuilder, description);

    codeBuilder.append(String.format("** methods **%n"));
    addMethods(codeBuilder, description);

    codeBuilder.append("}");

    return codeBuilder.toString();
  }

  public StringBuilder addImports(StringBuilder codeBuilder, ClassDescription description) {
    if (!description.getImports().isEmpty()) {
      for (int i = 0; i < description.getImports().size(); i ++) {
        codeBuilder.append(String.format("import %s;%n", description.getImports().get(i)));
      }
    }

    return codeBuilder;
  }

  public StringBuilder addHeader(StringBuilder codeBuilder, ClassDescription description) {
    codeBuilder.append(String.format(
        "public %s %s%n", description.getType(), description.getName()
    ));

    if (!description.getImplementations().isEmpty()) {
      codeBuilder.append("implements ");
      int numImplementations = description.getImplementations().size();
      for (int i = 0; i < numImplementations; i++) {
        codeBuilder.append(description.getImplementations().get(i));

        if (i < numImplementations - 1) {
          codeBuilder.append(", ");
        }
      }
    }

    if (!(description.getExtension() == null)) {
      codeBuilder.append(String.format("extends %s%n", description.getExtension()));
    }

    codeBuilder.append(String.format("{%n"));

    return codeBuilder;
  }

  public StringBuilder addVariables(StringBuilder codeBuilder, ClassDescription description) {
    for (int i = 0; i < description.getVariables().size(); i++) {
      codeBuilder.append(String.format("%s%n", description.getVariables().get(i)));
    }

    return codeBuilder;
  }

  public StringBuilder addConstructor(StringBuilder codeBuilder, ClassDescription description) {
    if (description.getType().equals(ClassDescription.CLASS)) {
      codeBuilder.append(String.format(
          "%s %s() {%n",
          description.getConstructorAccess(), description.getName()
      ));

      codeBuilder.append(description.getConstructorBody()); // for observable, must do super(new Object())

      codeBuilder.append("}%n");
    }

    return codeBuilder;
  }

  public StringBuilder addMethods(StringBuilder codeBuilder, ClassDescription description) {
    for (int i = 0; i < description.getMethods().size(); i++) {
      codeBuilder.append(description.getMethods().get(i));
      codeBuilder.append(String.format("%n%n"));
    }

    return codeBuilder;
  }

  public ClassDescription getClassDescription(Box box) {
    // TODO: remove this entire method
    // should just be able to do this instead if using JGS decorator pattern
//    firstElement.updateDescription(description);

    ClassDescription description = new ClassDescription();

    for (int i = 0; i < box.getDecorators().size(); i++) {
      box.getDecorators().get(i).updateDescription(description);
    }

    return description;
  }
}
