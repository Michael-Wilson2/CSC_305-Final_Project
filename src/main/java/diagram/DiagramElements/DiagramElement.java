package diagram.DiagramElements;

import code.ClassDescription;
import java.awt.*;
import java.io.Serializable;

public abstract class DiagramElement implements Serializable {
  protected Rectangle bounds;

  public DiagramElement(int x, int y, int w, int h) {
    this.bounds = new Rectangle(x, y, w, h);
  }

  public abstract void draw(Graphics g);

  public abstract DiagramElement occupies(int x, int y);

  public abstract ClassDescription updateDescription(ClassDescription description);

  public abstract void addConnection(DiagramElement connection);

  public void move(Point pointerDelta) {
    bounds.setLocation(bounds.x - pointerDelta.x, bounds.y - pointerDelta.y);
  }

  public Rectangle getBounds() {
    return this.bounds;
  }

  public void setPosition(int x, int y) {
    this.bounds.setLocation(x - bounds.width / 2, y - bounds.height / 2);
  }

  public Box getBox() {
    DiagramElement traversalElement = this;
    while (traversalElement instanceof BoxDecorator) {
      traversalElement = ((BoxDecorator) traversalElement).getNext();
    }

    if (!(traversalElement instanceof Box)) {
      System.out.println("error getting box from element!!");
    }

    return (Box) traversalElement;
  }
}
