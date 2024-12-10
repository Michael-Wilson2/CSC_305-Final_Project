package wilson;

import java.awt.*;
import java.util.ArrayList;

public class Box extends DiagramElement {
  public static final int DEFAULT_SIZE = 100;

  private String name;
  private ArrayList<BoxDecorator> decorators;
  private ArrayList<BoxConnection> connections;

  public Box(int x, int y, int w, int h, String name) {
    super(x, y, w, h);
    this.name = name;
    this.decorators = new ArrayList<>();
    this.connections = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public void draw(Graphics g) {
    // Draw connections
    g.setColor(Color.BLACK);
    for (BoxConnection boxConnection : this.connections) {
      Box box = boxConnection.getTo();
      g.drawLine((int) bounds.getCenterX(),
              (int) bounds.getCenterY(),
              (int) box.bounds.getCenterX(),
              (int) box.bounds.getCenterY());
    }

    // Draw box
    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

    // Draw name
    g.setColor(Color.WHITE);
    g.drawString(this.name, bounds.x, (int) bounds.getCenterY());

    drawDecorators(g);
  }

  @Override
  public boolean occupies(int x, int y) {
    return this.bounds.contains(x, y) || getDecoratorAtLocation(x, y) != null;
  }

  @Override
  public void addConnection(DiagramElement connection) {
    if (!(connection instanceof Box)) {
      System.out.println("Unexpected pls fix"); // TODO: make this a logger or exception
      return;
    }
    BoxConnection boxConnection = new BoxConnection(this, (Box) connection, Repository.getInstance().getConnector());
    connections.add(boxConnection);
  }

  public BoxDecorator getDecoratorAtLocation(int x, int y) {
    for (BoxDecorator decorator : decorators) {
      if (decorator.occupies(x, y)) {
        return decorator;
      }
    }
    return null;
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
