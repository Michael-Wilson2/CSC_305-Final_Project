package diagram.DiagramElements;

import code.ClassDescription;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public abstract class BoxDecorator extends DiagramElement {
  public static int DEFAULT_DECORATOR_RADIUS = 50;
  protected ArrayList<BoxDecorator> connections;
  protected DiagramElement diagramElement;

  public BoxDecorator(int x, int y) {
    super(x, y, DEFAULT_DECORATOR_RADIUS, DEFAULT_DECORATOR_RADIUS);
    this.connections = new ArrayList<>();
  }

  @Override
  public void draw(Graphics g) {
    if (diagramElement != null) {
      diagramElement.draw(g);
    }

    // Draw circle
    g.setColor(new Color(0xf0be5f));
    g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);

    // Draw connections
    g.setColor(Color.BLACK);
    for (BoxDecorator boxDecorator : connections) {
      g.drawLine((int) bounds.getCenterX(), (int) bounds.getCenterY(),
              (int) boxDecorator.bounds.getCenterX(), (int) boxDecorator.bounds.getCenterY());
    }
  }

  @Override
  public DiagramElement occupies(int x, int y) {
    Ellipse2D ellipse = new Ellipse2D.Double(bounds.x, bounds.y, bounds.width, bounds.height);
    if (ellipse.contains(x, y)) {
      return this;
    }
    if (diagramElement != null) {
      return diagramElement.occupies(x, y);
    }
    return null;
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    return this.diagramElement.updateDescription(description); // should traverse linked list w/ decorator pattern
  }

  @Override
  public void move(Point pointerDelta) {
    if (diagramElement != null) {
      diagramElement.move(pointerDelta);
    }
    super.move(pointerDelta);
  }

  public abstract void addConnection(DiagramElement connection);

  public ArrayList<BoxDecorator> getConnections() {
    return connections;
  }

  public void drawEmoji(String emoji, Graphics g) {
    g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
    int offsetX = -10;
    int offsetY = 8;
    g.drawString(emoji, (int) bounds.getCenterX() + offsetX, (int) bounds.getCenterY() + offsetY);
  }

  public void add(DiagramElement diagramElement) {
    this.diagramElement = diagramElement;
  }

  public DiagramElement getNext() {
    return this.diagramElement;
  }
}
