package wilson;

import java.awt.*;

public class BoxDecorator extends DiagramElement {
  protected DiagramElement next;

  public BoxDecorator(int x, int y, int w, int h) {
    super(x, y, w, h);
    this.next = null;
  }

  public BoxDecorator(int x, int y, int w, int h, DiagramElement next) {
    super(x, y, w, h);
    this.next = next;
  }

  @Override
  public void draw(Graphics g) {
    if (this.next != null) {
      next.draw(g);
    }
  }

  public void add(DiagramElement element) {
    this.next = element;
  }

  public DiagramElement getNext() {
    return this.next;
  }
}
