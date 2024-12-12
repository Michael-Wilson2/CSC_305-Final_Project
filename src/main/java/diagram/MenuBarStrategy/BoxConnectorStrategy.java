package diagram.MenuBarStrategy;

import diagram.Repository;

public class BoxConnectorStrategy implements MenuBarStrategy {
  @Override
  public void executeMenuAction(String itemName) {
    // Item name will be one of Aggregation, Composition, etc.
    Repository.getInstance().setConnector(itemName);
  }
}
