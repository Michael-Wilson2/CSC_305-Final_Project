package project.diagram.MenuBarStrategy;

import project.Repository;

/** Strategy for when "Box Decorator" menu bar item is pressed.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class BoxConnectorStrategy implements MenuBarStrategy {
  @Override
  public void executeMenuAction(String itemName) {
    // Item name will be one of Aggregation, Composition, etc.
    Repository.getInstance().setConnector(itemName);
  }
}
