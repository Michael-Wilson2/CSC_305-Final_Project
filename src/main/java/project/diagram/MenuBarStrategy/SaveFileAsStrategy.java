package project.diagram.MenuBarStrategy;

import project.Repository;

/** Strategy for when "Save as" menu bar item is pressed.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
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
