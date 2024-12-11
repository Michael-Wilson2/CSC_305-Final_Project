package code;

import java.util.ArrayList;
import java.util.List;

public class ClassDescriptionFactory extends ClassDescription {
  private String productName;

  public ClassDescriptionFactory(ClassDescription description) {
    super();
    copyDescription(description);
  }

  private void copyDescription(ClassDescription description) {
    setImports(description.getImports());
    setType(description.getType());
    setName(description.getName());
    setImplementations(description.getImplementations());
    setExtension(description.getExtension());
    setVariables(description.getVariables());
    setConstructorAccess(description.getConstructorAccess());
    setConstructorBody(description.getConstructorBody());
    setMethods(description.getMethods());
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductName() {
    return productName;
  }
}
