package wilson;

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
    if (e.getButton() == MouseEvent.BUTTON1) {
      // left click -- either add a new Box or rename an existing one
      if (!Repository.getInstance().isOccupied(e.getX(), e.getY())) {
        // if clicked in an empty space, add a new Box
        String name = "Box";
        Box box = new Box(
            e.getX() - (Box.DEFAULT_SIZE / 2),
            e.getY() - (Box.DEFAULT_SIZE / 2),
            Box.DEFAULT_SIZE,
            Box.DEFAULT_SIZE,
            String.format("%s%02d", name, Repository.getInstance().size())
        );

        Repository.getInstance().add(box);
      }

      else {
        // clicked on an existing box, so rename it
      }
    }

    else if (e.getButton() == MouseEvent.BUTTON3) {
      // right click -- display popup menu
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {

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
