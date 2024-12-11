package wilson.DiagramElements;

import java.awt.*;

public abstract class DiagramElement {
  protected boolean isSelected;
  protected Rectangle bounds;

  public DiagramElement(int x, int y, int w, int h) {
    this.bounds = new Rectangle(x, y, w, h);
  }

  public abstract void draw(Graphics g);

  public abstract DiagramElement occupies(int x, int y);

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }

  public boolean isSelected() {
    return this.isSelected;
  }

  public void setPosition(int x, int y) {
    this.bounds.setLocation(x - bounds.width / 2, y - bounds.height / 2);
  }

  public abstract void addConnection(DiagramElement connection);

  public void move(Point pointerDelta) {
    bounds.setLocation(bounds.x - pointerDelta.x, bounds.y - pointerDelta.y);
  }

  public Rectangle getBounds() {
    return this.bounds;
  }
}
