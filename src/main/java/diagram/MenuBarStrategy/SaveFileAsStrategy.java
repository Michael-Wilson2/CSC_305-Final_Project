package diagram.MenuBarStrategy;

import diagram.Repository;

public class SaveFileAsStrategy implements MenuBarStrategy {
  @Override
  public void executeMenuAction(String itemName) {
    FileChooser fileChooser = new FileChooser("Save File As...");
    String filePath = fileChooser.getSaveFilePath();
    if (filePath != null) {
      Repository.getInstance().saveDiagramElements(filePath);
      Repository.getInstance().setFilePath(filePath);
    }
  }
}
