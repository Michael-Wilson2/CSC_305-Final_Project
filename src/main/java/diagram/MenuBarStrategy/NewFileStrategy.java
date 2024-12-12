package diagram.MenuBarStrategy;

import diagram.Repository;

public class NewFileStrategy implements MenuBarStrategy {
  @Override
  public void executeMenuAction(String itemName) {
    Repository.getInstance().resetDiagram();
  }
}
