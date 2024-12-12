package diagram;

import diagram.DiagramElements.DiagramElement;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/** The diagram JPanel. Draws whatever the user has created or loaded.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class PanelDiagram extends JPanel implements PropertyChangeListener {
  public PanelDiagram() {
    setBackground(new Color(0xc6dace));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Paint elements
    for (DiagramElement element : Repository.getInstance().getElements()) {
      element.draw(g);
    }

    // Paint "in-progress" connection
    Point lineStart = Repository.getInstance().getLineStart();
    if (lineStart != null) {
      Point pointer = Repository.getInstance().getPointer();
      g.setColor(Color.BLACK);
      g.drawLine(lineStart.x, lineStart.y, pointer.x, pointer.y);
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("repaint")) {
      repaint();
    }
  }
}
