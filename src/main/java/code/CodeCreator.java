package code;

import diagram.DiagramElements.*;
import diagram.Repository;

import java.util.Scanner;

public class CodeCreator {
  public static final String TAB = "    "; // four spaces
  public static final String BOX_NAME_PLACEHOLDER = "<boxName>";
  public static final String PRODUCT_NAME_PLACEHOLDER = "<productName>";

  // need to write code for each box in the diagram

  /*
   * public <class / interface> <box name>
   * [OPTIONAL] implements ...
   * [OPTIONAL] extends ___
   * {
   *
   *   // variables
   *   [OPTIONAL] int var1;
   *
   *   // constructor
   *   <public / private> <box name>() {
   *     // do super(new Object()) for PCS, otherwise leave blank
   *   }
   *
   *   // methods...
   *   [OPTIONAL] public void method1() {
   *
   *   }
   * }
   */

  public String createCode(DiagramElement element) {
//    ClassDescription description = getClassDescription((Box) element); // use before refactoring decorator

    // TODO: should just be able to return this line instead using decorator pattern
    ClassDescription description = element.updateDescription(new ClassDescription());

    StringBuilder codeBuilder = new StringBuilder(); // TODO: can chain methods?
    addImports(codeBuilder, description);
    addHeader(codeBuilder, description);
    addVariables(codeBuilder, description);
    addConstructor(codeBuilder, description);
    addMethods(codeBuilder, description);
    codeBuilder.append("}");

    insertBoxNames(codeBuilder, description);

    if (codeBuilder.indexOf(PRODUCT_NAME_PLACEHOLDER) > 0) {
      String productName = getProductName((Factory) element);
      if (productName != null) {
        insertProductNames(codeBuilder, productName);
      }
    }

    return codeBuilder.toString();
  }

  private StringBuilder addImports(StringBuilder codeBuilder, ClassDescription description) {
    if (!description.getImports().isEmpty()) {
      for (int i = 0; i < description.getImports().size(); i ++) {
        codeBuilder.append(String.format("import %s;%n", description.getImports().get(i)));
      }
      codeBuilder.append(String.format("%n"));
    }

    return codeBuilder;
  }

  private StringBuilder addHeader(StringBuilder codeBuilder, ClassDescription description) {
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
        } else {
          codeBuilder.append(String.format("%n"));
        }
      }
    }

    if (!(description.getExtension() == null)) {
      codeBuilder.append(String.format("extends %s%n", description.getExtension()));
    }

    codeBuilder.append(String.format("{%n"));
    return codeBuilder;
  }

  private StringBuilder addVariables(StringBuilder codeBuilder, ClassDescription description) {
    if (!description.getVariables().isEmpty()) {
      for (int i = 0; i < description.getVariables().size(); i++) {
        codeBuilder.append(String.format(TAB + "%s%n", description.getVariables().get(i)));
      }
      codeBuilder.append(String.format("%n"));
    }

    return codeBuilder;
  }

  private StringBuilder addConstructor(StringBuilder codeBuilder, ClassDescription description) {
    if (description.getType().equals(ClassDescription.CLASS)) {
      codeBuilder.append(String.format(
          TAB + "%s %s() {%n",
          description.getConstructorAccess(), description.getName()
      ));

      if (description.getConstructorBody().isEmpty()) {
        codeBuilder.append(String.format("%n"));
      } else {
        codeBuilder.append(String.format(
            TAB + TAB + description.getConstructorBody() // for observable, must do super(new Object())
        ));
      }

      codeBuilder.append(String.format(TAB + "}%n%n"));
    }

    return codeBuilder;
  }

  private StringBuilder addMethods(StringBuilder codeBuilder, ClassDescription description) {
    if (!description.getMethods().isEmpty()) {
      for (int i = 0; i < description.getMethods().size(); i++) {
        String method = description.getMethods().get(i);
        Scanner scanner = new Scanner(method);
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          codeBuilder.append(String.format(
              TAB + line + String.format("%n")
          ));
        }
        codeBuilder.append(String.format("%n"));
      }
    }

    return codeBuilder;
  }

  private StringBuilder insertBoxNames(StringBuilder codeBuilder, ClassDescription description) {
    int replaceIndex;
    while ((replaceIndex = codeBuilder.indexOf(BOX_NAME_PLACEHOLDER)) > 0) {
      codeBuilder.replace(replaceIndex, replaceIndex + BOX_NAME_PLACEHOLDER.length(), description.getName());
    }
    return codeBuilder;
  }

  private StringBuilder insertProductNames(StringBuilder codeBuilder, String productName) {
    int replaceIndex;
    while ((replaceIndex = codeBuilder.indexOf(PRODUCT_NAME_PLACEHOLDER)) > 0) {
      codeBuilder.replace(replaceIndex, replaceIndex + PRODUCT_NAME_PLACEHOLDER.length(), productName);
    }
    return codeBuilder;
  }

  private String getProductName(Factory factory) {
    // factory decorator --> product decorator --> product box --> product box's name
    if (factory.getConnecctions().isEmpty()) {
      return null;
    }

    Product product = (Product) factory.getConnecctions().getFirst();
    return product.getBox().getName();
  }
}
