package wilson;

import java.awt.*;

public abstract class DiagramElement {
  protected boolean isSelected;
  protected Rectangle bounds;

  public DiagramElement(int x, int y, int w, int h) {
    this.bounds = new Rectangle(x, y, w, h);
  }

  public abstract void draw(Graphics g);

  public boolean occupies(int x, int y) {
    if (this instanceof BoxDecorator decorator && decorator.next != null) {
      return decorator.next.occupies(x, y);
    }

    return this.bounds.contains(new Point(x, y));
  }

  public void setSelected(boolean isSelected) {
    this.isSelected = isSelected;
  }

  public boolean isSelected() {
    return this.isSelected;
  }

  public void setPosition(int x, int y) {
    this.bounds.setLocation(x - bounds.width / 2, y - bounds.height / 2);
  }
}
