package wilson;

import code.PanelCode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class Main extends JFrame {
  public Main() {
    // Diagram panel
    PanelDiagram diagramPanel = new PanelDiagram();
    GUIController controller = new GUIController();
    diagramPanel.addMouseListener(controller);
    diagramPanel.addMouseMotionListener(controller);
    Repository.getInstance().addPropertyChangeListener(diagramPanel);

    // Code panel
    PanelCode codePanel = new PanelCode();
    Repository.getInstance().addPropertyChangeListener(codePanel);

    // Tabbed pane
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Draw Area", diagramPanel);
    tabbedPane.addTab("Code", codePanel);

    add(tabbedPane);

    // Menu bar
    MenuBar menuBar = new MenuBar();
    setJMenuBar(menuBar);

    Repository.getInstance().setFrame(this);
    repaint();
  }

  public static void main(String[] args) {
    Main main = new Main();
    main.setTitle("CSC 305 Final Project - Michael Wilson and Andrew Kulakovsky");
    main.setSize(1000, 600);
    main.setResizable(false);
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setLocationRelativeTo(null);
    main.setVisible(true);
  }
}