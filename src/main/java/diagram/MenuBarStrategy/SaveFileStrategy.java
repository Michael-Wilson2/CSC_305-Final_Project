package diagram.MenuBarStrategy;

import diagram.Repository;

/** Strategy for when "Save" menu bar item is pressed.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
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
