package wilson;

import code.ClassDescription;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public abstract class BoxDecorator extends DiagramElement {
  public static int DEFAULT_DECORATOR_RADIUS = 50;

  private Box box;
  private int xOffset;
  private int yOffset;
  protected ArrayList<BoxDecorator> connections;
  protected DiagramElement next;

  public BoxDecorator(int x, int y, Box box) {
    super(x, y, DEFAULT_DECORATOR_RADIUS, DEFAULT_DECORATOR_RADIUS);
    this.box = box;
    this.xOffset = box.bounds.x - x;
    this.yOffset = box.bounds.y - y;
    this.connections = new ArrayList<>();
    this.next = null;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(new Color(0xf0be5f));
    g.fillOval(getRelativeX(), getRelativeY(), bounds.width, bounds.height);
  }

  public void drawConnections(Graphics g) {
    g.setColor(Color.BLACK);
    for (BoxDecorator boxDecorator : connections) {
      g.drawLine(getCenter().x, getCenter().y, boxDecorator.getCenter().x, boxDecorator.getCenter().y);
    }
  }

  @Override
  public boolean occupies(int x, int y) {
    Ellipse2D ellipse = new Ellipse2D.Double(getRelativeX(), getRelativeY(), bounds.width, bounds.height);
    return ellipse.contains(x, y);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    return this.next.updateDescription(description); // should traverse linked list
  }

  public abstract void addConnection(DiagramElement connection);

  public void add(DiagramElement next) {
    this.next = next;
  }

  public DiagramElement getNext() {
    return this.next;
  }

  public int getRelativeX() {
    return box.bounds.x - xOffset;
  }

  public int getRelativeY() {
    return box.bounds.y - yOffset;
  }

  public void drawEmoji(String emoji, Graphics g) {
    g.setFont(new Font("Arial", Font.PLAIN, 20));
    int offsetX = -10;
    int offsetY = 8;
    g.drawString(emoji, getCenter().x + offsetX, getCenter().y + offsetY);
  }

  public Point getCenter() {
    int offset = DEFAULT_DECORATOR_RADIUS / 2;
    return new Point(getRelativeX() + offset, getRelativeY() + offset);
  }
}
