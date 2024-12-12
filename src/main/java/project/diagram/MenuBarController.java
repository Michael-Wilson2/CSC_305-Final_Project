package project.diagram;

import project.diagram.MenuBarStrategy.*;
import project.diagram.MenuBarStrategy.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** The GUI's menu bar controller which listens for and handles actions.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
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
        break;
      case "Open...":
        menuBarStrategy = new OpenFileStrategy();
        break;
      case "Save as...":
        menuBarStrategy = new SaveFileAsStrategy();
        break;
      case "Save":
        menuBarStrategy = new SaveFileStrategy();
        break;
      case "New...":
        menuBarStrategy = new NewFileStrategy();
        break;
    }

    if (menuBarStrategy != null) {
      menuBarStrategy.executeMenuAction(e.getActionCommand());
    }
  }
}
