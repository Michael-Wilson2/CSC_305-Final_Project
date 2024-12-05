package wilson;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
  public Main() {
    PanelDiagram diagramPanel = new PanelDiagram();

    GUIController controller = new GUIController();
    diagramPanel.addMouseListener(controller);
    diagramPanel.addMouseMotionListener(controller);

    Repository.getInstance().addPropertyChangeListener(diagramPanel);

    setLayout(new GridLayout(1, 1));

    add(diagramPanel);
    repaint();
  }

  public static void main(String[] args) {
    Main main = new Main();
    main.setTitle("CSC 305 Final Project - Michael Wilson");
    main.setSize(1280, 720);
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    main.setLocationRelativeTo(null);
    main.setVisible(true);
  }
}