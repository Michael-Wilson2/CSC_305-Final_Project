package wilson.MenuBarStrategy;

import wilson.Repository;

public class BoxConnectorStrategy implements MenuBarStrategy {
  @Override
  public void doTheThing(String itemName) {
    // Item name will be one of Aggregation, Composition, etc.
    Repository.getInstance().setConnector(itemName);
  }
}
