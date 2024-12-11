package diagram.DiagramElements;

import code.ClassDescription;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public abstract class BoxDecorator extends DiagramElement {
  public static int DEFAULT_DECORATOR_RADIUS = 50;

//  private int xOffset;
//  private int yOffset;
  protected ArrayList<BoxDecorator> connections;
  protected DiagramElement diagramElement;

  public BoxDecorator(int x, int y) {
    super(x, y, DEFAULT_DECORATOR_RADIUS, DEFAULT_DECORATOR_RADIUS);
//    this.xOffset = box.bounds.x - x;
//    this.yOffset = box.bounds.y - y;
    this.connections = new ArrayList<>();
  }

  @Override
  public void draw(Graphics g) {
    if (diagramElement != null) {
      diagramElement.draw(g);
    }
    g.setColor(new Color(0xf0be5f));
    g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);

    drawConnections(g);
  }

  public void drawConnections(Graphics g) {
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
      DiagramElement childDiagramElementOccupies = diagramElement.occupies(x, y);
      if (childDiagramElementOccupies != null) {
        return childDiagramElementOccupies;
      }
    }
    return null;
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    return this.diagramElement.updateDescription(description); // should traverse linked list
  }

  public abstract void addConnection(DiagramElement connection);

  public int getRelativeX() {
//    return box.bounds.x - xOffset;
    return 0;
  }

  public int getRelativeY() {
//    return box.bounds.y - yOffset;
    return 0;
  }

  public void drawEmoji(String emoji, Graphics g) {
    g.setFont(new Font("Arial", Font.PLAIN, 20));
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

  @Override
  public void move(Point pointerDelta) {
    super.move(pointerDelta);
    if (diagramElement != null) {
      diagramElement.move(pointerDelta);
    }
  }
}
