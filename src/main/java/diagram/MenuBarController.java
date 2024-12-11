package wilson;

import wilson.MenuBarStrategy.AboutStrategy;
import wilson.MenuBarStrategy.BoxConnectorStrategy;
import wilson.MenuBarStrategy.MenuBarStrategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarController implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    MenuBarStrategy menuBarStrategy = null;

    switch (e.getActionCommand()) {
      case "Aggregation":
      case "Composition":
      case "Association":
      case "Inheritance":
      case "Realization":
        menuBarStrategy = new BoxConnectorStrategy();
        break;
      case "About":
        menuBarStrategy = new AboutStrategy();
    }

    if (menuBarStrategy != null) {
      menuBarStrategy.doTheThing(e.getActionCommand());
    }
  }
}
