package wilson;

import wilson.DiagramElements.Box;
import wilson.DiagramElements.BoxDecorator;
import wilson.DiagramElements.DiagramElement;

import javax.swing.*;
import java.awt.*;
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
    DiagramElement diagramElement = Repository.getInstance().getElementAtLocation(x, y);
    if (diagramElement != null) {
      if (diagramElement instanceof Box) {
        int offset = BoxDecorator.DEFAULT_DECORATOR_RADIUS / 2;
        DiagramElement rootElement = Repository.getInstance().getRootElementAtLocation(x, y);
        PopupDecoratorList popup = new PopupDecoratorList(rootElement, x - offset, y - offset);
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

    DiagramElement element = Repository.getInstance().getElementAtLocation(x, y);
    if (element == null) { // Clicked empty space
      handleLeftClickInEmptySpace(x, y);
    } else { // Clicked non-empty space
      if (element instanceof BoxDecorator boxDecorator) {
        handleLeftClickOnBoxDecorator(boxDecorator);
      } else if (element instanceof Box box) {
        handleLeftClickOnBox(box);
      }
    }
  }

  private void handleLeftClickAfterConnectorSet(int x, int y) {
    DiagramElement element = Repository.getInstance().getElementAtLocation(x, y);
    if (element instanceof Box box) {
      Repository.getInstance().setConnectingBox(box);
      Repository.getInstance().setLineStart(new Point((int) box.getBounds().getCenterX(), (int) box.getBounds().getCenterY()));
    }
  }

  private void handleLeftClickWhileConnectingBox(int x, int y) {
    DiagramElement diagramElement = Repository.getInstance().getElementAtLocation(x, y);
    if (diagramElement instanceof Box box) {
      Box connectingBox = Repository.getInstance().getConnectingBox();
      box.addConnection(connectingBox);
      Repository.getInstance().repaint();

      Repository.getInstance().setConnectingBox(null);
      Repository.getInstance().setConnector(null);
      Repository.getInstance().setLineStart(null);
    }
  }

  private void handleLeftClickWhileConnectingDecorator(int x, int y) {
   DiagramElement diagramElement = Repository.getInstance().getElementAtLocation(x, y);
    if (diagramElement instanceof BoxDecorator boxDecorator) {
      // TODO: If clicked box decorator == connection box decorator, cancel the connection
      boxDecorator.addConnection(Repository.getInstance().getConnectingDecorator());
      Repository.getInstance().repaint();

      Repository.getInstance().setConnectingDecorator(null);
      Repository.getInstance().setIsConnectingDecorator(false);
      Repository.getInstance().setLineStart(null);
    }
  }

  private void handleLeftClickInEmptySpace(int x, int y) {
    int offset = wilson.DiagramElements.Box.DEFAULT_SIZE / 2;
    String name = String.format("%s%02d", "Box", Repository.getInstance().size());
    wilson.DiagramElements.Box box = new wilson.DiagramElements.Box(x - offset, y - offset, wilson.DiagramElements.Box.DEFAULT_SIZE, wilson.DiagramElements.Box.DEFAULT_SIZE, name);
    Repository.getInstance().add(box);
  }

  private void handleLeftClickOnBox(wilson.DiagramElements.Box box) {
    JFrame frame = Repository.getInstance().getFrame();
    String newName = JOptionPane.showInputDialog(frame, "New Name", "Rename", JOptionPane.PLAIN_MESSAGE);
    if (newName != null) {
      Repository.getInstance().setBoxName(box, newName);
    }
  }

  private void handleLeftClickOnBoxDecorator(BoxDecorator boxDecorator) {
    Repository.getInstance().setConnectingDecorator(boxDecorator);
    Repository.getInstance().setIsConnectingDecorator(true);
    Repository.getInstance().setLineStart(new Point((int) boxDecorator.getBounds().getCenterX(),
            (int) boxDecorator.getBounds().getCenterY()));
  }

  @Override
  public void mousePressed(MouseEvent e) {
    DiagramElement element = Repository.getInstance().getElementAtLocation(e.getX(), e.getY());
//    if (element instanceof Box box) {
    Repository.getInstance().setSelectedRootElement(Repository.getInstance().getRootElementAtLocation(e.getX(), e.getY()));
//    }
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    Repository.getInstance().setPointer(e.getX(), e.getY());
    DiagramElement element = Repository.getInstance().getSelectedRootElement();
    if (element != null) {
      Repository.getInstance().moveElement(element);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Repository.getInstance().setSelectedRootElement(null);
    Repository.getInstance().setSelectedElement(null);
  }

  @Override
  public void componentResized(ComponentEvent e) {

  }

  @Override
  public void mouseMoved(MouseEvent e) {
    Repository.getInstance().setPointer(e.getX(), e.getY());
    if (Repository.getInstance().getLineStart() != null) {
      Repository.getInstance().repaint();
    }
  }

  // unused methods below -----------------------------------------------------

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void componentMoved(ComponentEvent e) {}

  @Override
  public void componentShown(ComponentEvent e) {}

  @Override
  public void componentHidden(ComponentEvent e) {}
}
