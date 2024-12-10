package wilson;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PanelDiagram extends JPanel implements PropertyChangeListener {
  public PanelDiagram() {
    setBackground(new Color(0xc6dace));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    List<Box> boxes = Repository.getInstance().getBoxes();
    for (Box box : boxes) {
      box.drawConnections(g);
    }
    for (Box box : boxes) {
      box.draw(g);
    }
    for (Box box : boxes) {
      box.drawDecoratorConnections(g);
    }
    for (Box box : boxes) {
      box.drawDecorators(g);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("repaint")) {
      repaint();
    }
  }
}
