package wilson;

import javax.swing.*;
import java.awt.event.*;

// TODO: Use strategy pattern here
public class GUIController implements MouseListener, MouseMotionListener, ComponentListener {
  int xOffset;
  int yOffset;

  public GUIController() {
    this.xOffset = 0;
    this.yOffset = 0;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
    if (e.getButton() == MouseEvent.BUTTON1) {
      handleLeftMouseClick(x, y);
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      handleRightMouseClick(x, y);
    }
  }

  private void handleRightMouseClick(int x, int y) {
    Box box = Repository.getInstance().getElementAtLocation(x, y);
    if (box != null) {
      BoxDecorator boxDecorator = box.getDecoratorAtLocation(x, y);
      if (boxDecorator == null) { // TODO: This ignores if clicking a decorator, but really, it should ignore if the new decorator will overlap
        int offset = BoxDecorator.DEFAULT_DECORATOR_RADIUS / 2;
        PopupDecoratorList popup = new PopupDecoratorList(box, x - offset, y - offset);
        popup.show(Repository.getInstance().getFrame(), x, y);
      }
    }
  }

  private void handleLeftMouseClick(int x, int y) {
    if (Repository.getInstance().getConnectingDecorator() != null) {
      handleLeftClickWhileConnectingDecorator(x, y);
      return;
    } else if (Repository.getInstance().getConnectingBox() != null) {
      handleLeftClickWhileConnectingBox(x, y);
      return;
    } else if (Repository.getInstance().getConnector() != null) {
      handleLeftClickAfterConnectorSet(x, y);
      return;
    }

    Box box = Repository.getInstance().getElementAtLocation(x, y);
    if (box == null) { // Clicked empty space
      handleLeftClickInEmptySpace(x, y);
    } else { // Clicked non-empty space
      BoxDecorator boxDecorator = box.getDecoratorAtLocation(x, y);
      if (boxDecorator != null) {
        handleLeftClickOnBoxDecorator(boxDecorator);
      } else {
        handleLeftClickOnBox(box);
      }
    }
  }

  private void handleLeftClickAfterConnectorSet(int x, int y) {
    Box box = Repository.getInstance().getElementAtLocation(x, y);
    if (box != null) {
      Repository.getInstance().setConnectingBox(box);
    }
  }

  private void handleLeftClickWhileConnectingBox(int x, int y) {
    Box box = Repository.getInstance().getElementAtLocation(x, y);
    if (box != null) {
      // TODO: If clicked box == connection box, cancel the connection
      box.addConnection(Repository.getInstance().getConnectingBox());
      Repository.getInstance().repaint();

      Repository.getInstance().setConnectingBox(null);
      Repository.getInstance().setConnector(null);
    }
  }

  private void handleLeftClickWhileConnectingDecorator(int x, int y) {
    Box box = Repository.getInstance().getElementAtLocation(x, y);
    if (box != null) {
      BoxDecorator boxDecorator = box.getDecoratorAtLocation(x, y);
      if (boxDecorator != null) {
        // TODO: If clicked box decoratro == connection box decorator, cancel the connection
        boxDecorator.addConnection(Repository.getInstance().getConnectingDecorator());
        Repository.getInstance().repaint();

        Repository.getInstance().setConnectingDecorator(null);
        Repository.getInstance().setIsConnectingDecorator(false);
      }
    }
  }

  private void handleLeftClickInEmptySpace(int x, int y) {
    int offset = Box.DEFAULT_SIZE / 2;
    String name = String.format("%s%02d", "Box", Repository.getInstance().size());
    Box box = new Box(x - offset, y - offset, Box.DEFAULT_SIZE, Box.DEFAULT_SIZE, name);
    Repository.getInstance().add(box);
  }

  private void handleLeftClickOnBox(Box box) {
    JFrame frame = Repository.getInstance().getFrame();
    String newName = JOptionPane.showInputDialog(frame, "New Name", "Rename", JOptionPane.PLAIN_MESSAGE);
    if (newName != null) {
      Repository.getInstance().setBoxName(box, newName);
    }
  }

  private void handleLeftClickOnBoxDecorator(BoxDecorator boxDecorator) {
    Repository.getInstance().setConnectingDecorator(boxDecorator);
    Repository.getInstance().setIsConnectingDecorator(true);
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
