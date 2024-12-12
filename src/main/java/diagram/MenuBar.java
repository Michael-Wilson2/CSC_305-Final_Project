package diagram;

import javax.swing.*;

/** The GUI's menu bar. Extends JMenuBar.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class MenuBar extends JMenuBar {
  public static JMenuItem[] FILE_MENU_ITEMS = {
          new JMenuItem("New..."),
          new JMenuItem("Open..."),
          new JMenuItem("Save as..."),
          new JMenuItem("Save")
  };

  public static JMenuItem[] BOX_CONNECTOR_MENU_ITEMS = {
          new JMenuItem("Aggregation"),
          new JMenuItem("Composition"),
          new JMenuItem("Association"),
          new JMenuItem("Inheritance"),
          new JMenuItem("Realization")
  };

  public static JMenu FILE = new JMenu("File");
  public static JMenu BOX_CONNECTOR = new JMenu("Box Connector");
  public static JMenu TOOLS = new JMenu("Tools");
  public static JMenu HELP = new JMenu("Help");

  public static JMenu[] jMenus = { FILE, BOX_CONNECTOR, TOOLS, HELP };

  public MenuBar() {
    MenuBarController menuBarController = new MenuBarController();

    for (JMenuItem item : FILE_MENU_ITEMS) {
      FILE.add(item);
      item.addActionListener(menuBarController);
    }
    for (JMenuItem item : BOX_CONNECTOR_MENU_ITEMS) {
      BOX_CONNECTOR.add(item);
      item.addActionListener(menuBarController);
    }
    JMenuItem run = new JMenuItem("Run");
    JMenuItem about = new JMenuItem("About");
    TOOLS.add(run);
    HELP.add(about);
    run.addActionListener(menuBarController);
    about.addActionListener(menuBarController);

    for (JMenu menu : jMenus) {
      menu.addActionListener(menuBarController);
      this.add(menu);
    }
  }
}
