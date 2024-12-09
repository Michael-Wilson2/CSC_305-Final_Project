package wilson;

import java.awt.*;
import java.util.ArrayList;

public class Box extends DiagramElement {
  public static final int DEFAULT_SIZE = 100;

  private String name;
  private ArrayList<BoxDecorator> decorators = new ArrayList<>();

  public Box(int x, int y, int w, int h, String name) {
    super(x, y, w, h);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

    g.setColor(Color.WHITE);
    g.drawString(this.name, bounds.x, (int) bounds.getCenterY());

    drawDecorators(g);
  }

  private void drawDecorators(Graphics g) {
    for (BoxDecorator decorator : decorators) {
      decorator.draw(g);
    }
  }

  public void addDecorator(BoxDecorator decorator) {
    decorators.add(decorator);
  }

  @Override
  public String toString() {
    return String.format(
        "%s centered on (%d, %d)", name, (int) bounds.getCenterX(), (int) bounds.getCenterY()
    );
  }
}
