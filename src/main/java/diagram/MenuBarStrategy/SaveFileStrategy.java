package diagram.MenuBarStrategy;

import diagram.Repository;

public class SaveFileStrategy implements MenuBarStrategy {
  @Override
  public void executeMenuAction(String itemName) {
    String filePath = Repository.getInstance().getFilePath();
    if (filePath != null) {
      Repository.getInstance().saveDiagramElements(filePath);
    } else {
      SaveFileAsStrategy saveFileAsStrategy = new SaveFileAsStrategy();
      saveFileAsStrategy.executeMenuAction(itemName);
    }
  }
}
