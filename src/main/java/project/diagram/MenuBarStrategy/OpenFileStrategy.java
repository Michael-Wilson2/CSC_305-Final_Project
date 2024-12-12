package project.diagram.MenuBarStrategy;

import project.Repository;

/** Strategy for when "Open" menu bar item is pressed.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
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
