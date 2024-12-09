package wilson;

import java.awt.*;

public class BoxDecorator extends DiagramElement {
  public static int DEFAULT_DECORATOR_RADIUS = 30;

  protected DiagramElement next;
  private Box box;
  private int relativeX;
  private int relativeY;

  public BoxDecorator(int x, int y, Box box) {
    super(x, y, DEFAULT_DECORATOR_RADIUS, DEFAULT_DECORATOR_RADIUS);
    this.box = box;
    this.next = null;

    this.relativeX = box.bounds.x - x;
    this.relativeY = box.bounds.y - y;
  }

  public BoxDecorator(int x, int y, int w, int h, DiagramElement next) {
    super(x, y, w, h);
    this.next = next;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.YELLOW);
    g.fillOval(box.bounds.x - relativeX, box.bounds.y - relativeY, bounds.width, bounds.height);
  }

  public void add(DiagramElement element) {
    this.next = element;
  }

  public DiagramElement getNext() {
    return this.next;
  }
}
