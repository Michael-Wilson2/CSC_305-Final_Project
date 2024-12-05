package wilson;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class PanelDiagram extends JPanel implements PropertyChangeListener {
  public PanelDiagram() {
    setBackground(new Color(230, 230, 230));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    List<DiagramElement> elements = Repository.getInstance().getElements();
    for (DiagramElement element : elements) {
      element.draw(g);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("repaint")) {
      repaint();
    }
  }
}
