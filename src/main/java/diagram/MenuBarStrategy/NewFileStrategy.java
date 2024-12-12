package diagram.MenuBarStrategy;

import diagram.Repository;

/** Strategy for when "New" menu bar item is pressed.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class NewFileStrategy implements MenuBarStrategy {
  @Override
  public void executeMenuAction(String itemName) {
    Repository.getInstance().resetDiagram();
  }
}
