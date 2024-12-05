package wilson;

import java.awt.*;

public class Box extends DiagramElement {
  public static final int DEFAULT_SIZE = 100;

  private String name;

  public Box(int x, int y, int w, int h, String name) {
    super(x, y, w, h);
    this.name = name;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

    g.setColor(Color.WHITE);
    g.drawString(this.name, bounds.x, (int) bounds.getCenterY());
  }

  @Override
  public String toString() {
    return String.format(
        "%s centered on (%d, %d)", name, (int) bounds.getCenterX(), (int) bounds.getCenterY()
    );
  }
}
