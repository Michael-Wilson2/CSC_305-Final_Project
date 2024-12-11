package wilson;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class Main extends JFrame {
  public Main() {
    // Diagram
    PanelDiagram diagramPanel = new PanelDiagram();
    GUIController controller = new GUIController();
    diagramPanel.addMouseListener(controller);
    diagramPanel.addMouseMotionListener(controller);
    Repository.getInstance().addPropertyChangeListener(diagramPanel);

    // File tree
    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("src");
    JTree fileTree = new JTree(rootNode);
    JScrollPane scrollPane = new JScrollPane(fileTree);

    // Text pane
    JTextPane textPane = new JTextPane();

    // File tree + text pane
    JPanel panelContainer = new JPanel(new GridLayout(1, 2));
    panelContainer.add(scrollPane);
    panelContainer.add(textPane);

    // Tabbed pane
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Draw Area", diagramPanel);
    tabbedPane.addTab("Code", panelContainer);

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