package diagram.MenuBarStrategy;

import diagram.Repository;

public class OpenFileStrategy implements MenuBarStrategy {
  @Override
  public void executeMenuAction(String itemName) {
    FileChooser fileChooser = new FileChooser("Load Diagram");
    String filePath = fileChooser.getOpenFilePath();
    if (filePath != null) {
      Repository.getInstance().loadDiagramElements(filePath);
      Repository.getInstance().setFilePath(filePath);
    }
  }
}
