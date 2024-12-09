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

  public static JMenu file = new JMenu("File");
  public static JMenu boxConnector = new JMenu("Box Connector");
  public static JMenu tools = new JMenu("Tools");
  public static JMenu help = new JMenu("Help");

  public static JMenu[] jMenus = { file, boxConnector, tools, help };

  public MenuBar() {
    for (JMenuItem item : FILE_MENU_ITEMS) {
      file.add(item);
    }
    for (JMenuItem item : BOX_CONNECTOR_MENU_ITEMS) {
      boxConnector.add(item);
    }
    tools.add(new JMenuItem("Run"));
    help.add(new JMenuItem("About"));

    MenuBarController menuBarController = new MenuBarController();

    for (JMenu menu : jMenus) {
      menu.addActionListener(menuBarController);
      this.add(menu);
    }
  }
}
