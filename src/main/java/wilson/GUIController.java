package wilson;

import javax.swing.*;
import java.awt.event.*;

public class GUIController implements MouseListener, MouseMotionListener, ComponentListener {
  int xOffset;
  int yOffset;

  public GUIController() {
    this.xOffset = 0;
    this.yOffset = 0;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Box element = Repository.getInstance().getElementAtLocation(e.getX(), e.getY());
    if (e.getButton() == MouseEvent.BUTTON1) {
      if (element == null) {
        String name = "Box";
        Box box = new Box(
            e.getX() - (Box.DEFAULT_SIZE / 2),
            e.getY() - (Box.DEFAULT_SIZE / 2),
            Box.DEFAULT_SIZE,
            Box.DEFAULT_SIZE,
            String.format("%s%02d", name, Repository.getInstance().size())
        );

        Repository.getInstance().add(box);
      } else {
        String newName = JOptionPane.showInputDialog(Repository.getInstance().getFrame(),
                "New Name",
                "Rename",
                JOptionPane.PLAIN_MESSAGE);
        Repository.getInstance().setBoxName(element, newName);
      }
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      PopupDecoratorList popup = new PopupDecoratorList(element, e.getX(), e.getY());
      popup.show(Repository.getInstance().getFrame(), e.getX(), e.getY());
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    Box box = Repository.getInstance().getElementAtLocation(e.getX(), e.getY());
    if (box != null) {
      Repository.getInstance().setBoxPosition(box, e.getX(), e.getY());
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void componentResized(ComponentEvent e) {

  }

  // unused methods below -----------------------------------------------------

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {}

  @Override
  public void componentMoved(ComponentEvent e) {}

  @Override
  public void componentShown(ComponentEvent e) {}

  @Override
  public void componentHidden(ComponentEvent e) {}
}
