package wilson;

import javax.swing.*;

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
    TOOLS.add(new JMenuItem("Run"));
    TOOLS.addActionListener(menuBarController);
    HELP.add(new JMenuItem("About"));
    HELP.addActionListener(menuBarController);

    for (JMenu menu : jMenus) {
      menu.addActionListener(menuBarController);
      this.add(menu);
    }
  }
}
