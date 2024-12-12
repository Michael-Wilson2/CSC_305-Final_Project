package code;

import diagram.DiagramElements.*;
import diagram.Repository;

import java.util.Scanner;

/** Contains methods to create a code "skeleton" for an element in the diagram
 * @author Michael Wilson
 * @version 1.0
 */
public class CodeCreator {
  public static final String TAB = "    "; // four spaces
  public static final String BOX_NAME_PLACEHOLDER = "<BoxName>";
  public static final String PRODUCT_NAME_PLACEHOLDER = "<ProductName>";
  public static final String HANDLER_NAME_PLACEHOLDER = "<HandlerName>";
  public static final String COMPONENT_NAME_PLACEHOLDER = "<ComponentName>";

  /** Create a code "skeleton" for the given diagram element
   *
   * @param element the Box or Decorator from the diagram to create code for
   * @return a String consisting of the code "skeleton" for the given element
   */
  public String createCode(DiagramElement element) {
    ClassDescription description = element.updateDescription(new ClassDescription());

    StringBuilder codeBuilder = new StringBuilder()
      .append(addImports(description))
      .append(addHeader(description))
      .append(addVariables(description))
      .append(addConstructor(description))
      .append(addMethods(description))
      .append("}");

    replacePlaceholders(codeBuilder, BOX_NAME_PLACEHOLDER, description.getName());

    if (codeBuilder.indexOf(PRODUCT_NAME_PLACEHOLDER) > 0) {
      String productName = getProductName((Factory) element);
      if (productName != null) {
        replacePlaceholders(codeBuilder, PRODUCT_NAME_PLACEHOLDER, productName);
      }
    }

    if (codeBuilder.indexOf(HANDLER_NAME_PLACEHOLDER) > 0) {
      String handlerName = getParentName(Repository.getInstance().getBox(description.getName()));
      if (handlerName != null) {
        replacePlaceholders(codeBuilder, HANDLER_NAME_PLACEHOLDER, handlerName);
      }
    }

    if (codeBuilder.indexOf(COMPONENT_NAME_PLACEHOLDER) > 0) {
      String topComponentName = getParentName(Repository.getInstance().getBox(description.getName()));
      if (topComponentName != null) {
        replacePlaceholders(codeBuilder, COMPONENT_NAME_PLACEHOLDER, topComponentName);
      }
    }

    return codeBuilder.toString();
  }

  private StringBuilder addImports(ClassDescription description) {
    StringBuilder importBuilder = new StringBuilder();

    if (!description.getImports().isEmpty()) {
      for (int i = 0; i < description.getImports().size(); i ++) {
        importBuilder.append(String.format("import %s;%n", description.getImports().get(i)));
      }
      importBuilder.append(String.format("%n"));
    }

    return importBuilder;
  }

  private StringBuilder addHeader(ClassDescription description) {
    StringBuilder headerBuilder = new StringBuilder();

    headerBuilder.append(String.format(
        "public %s %s%n", description.getType(), description.getName()
    ));

    if (!description.getImplementations().isEmpty()) {
      headerBuilder.append("implements ");
      int numImplementations = description.getImplementations().size();
      for (int i = 0; i < numImplementations; i++) {
        headerBuilder.append(description.getImplementations().get(i));

        if (i < numImplementations - 1) {
          headerBuilder.append(", ");
        } else {
          headerBuilder.append(String.format("%n"));
        }
      }
    }

    if (!(description.getExtension() == null)) {
      headerBuilder.append(String.format("extends %s%n", description.getExtension()));
    }

    headerBuilder.append(String.format("{%n"));
    return headerBuilder;
  }

  private StringBuilder addVariables(ClassDescription description) {
    StringBuilder variableBuilder = new StringBuilder();

    if (!description.getVariables().isEmpty()) {
      for (int i = 0; i < description.getVariables().size(); i++) {
        variableBuilder.append(String.format(TAB + "%s%n", description.getVariables().get(i)));
      }
      variableBuilder.append(String.format("%n"));
    }

    return variableBuilder;
  }

  private StringBuilder addConstructor(ClassDescription description) {
    StringBuilder constructorBuilder = new StringBuilder();

    if (description.getType().equals(ClassDescription.CLASS)) {
      constructorBuilder.append(String.format(
          TAB + "%s %s() {%n",
          description.getConstructorAccess(), description.getName()
      ));

      if (description.getConstructorBody().isEmpty()) {
        constructorBuilder.append(String.format("%n"));
      } else {
        constructorBuilder.append(String.format(
            TAB + TAB + description.getConstructorBody() // for observable, must do super(new Object())
        ));
      }

      constructorBuilder.append(String.format(TAB + "}%n%n"));
    }

    return constructorBuilder;
  }

  private StringBuilder addMethods(ClassDescription description) {
    StringBuilder methodBuilder = new StringBuilder();

    if (!description.getMethods().isEmpty()) {
      for (int i = 0; i < description.getMethods().size(); i++) {
        String method = description.getMethods().get(i);
        Scanner scanner = new Scanner(method);
        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();
          methodBuilder.append(String.format(
              TAB + line + String.format("%n")
          ));
        }
        methodBuilder.append(String.format("%n"));
      }
    }

    return methodBuilder;
  }

  private StringBuilder replacePlaceholders(StringBuilder codeBuilder, String placeholder, String replacement) {
    int replaceIndex;
    while ((replaceIndex = codeBuilder.indexOf(placeholder)) > 0) {
      codeBuilder.replace(replaceIndex, replaceIndex + placeholder.length(), replacement);
    }
    return codeBuilder;
  }

  private String getProductName(Factory factory) {
    // factory decorator --> product decorator --> product box --> product box's name
    if (factory.getConnections().isEmpty()) {
      return null;
    }

    Product product = (Product) factory.getConnections().getFirst();
    return product.getBox().getName();
  }

  private String getParentName(Box box) {
    if (box.getConnections().isEmpty()) {
      return null;
    }

    for (int i = 0; i < box.getConnections().size(); i++) {
      if (box.getConnections().get(i).type().equals("Inheritance")) {

        return box.getConnections().get(i).to().getName();
      }
    }
    return null;
  }
}
