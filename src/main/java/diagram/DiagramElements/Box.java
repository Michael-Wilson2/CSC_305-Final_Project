package diagram.DiagramElements;

import code.ClassDescription;
import diagram.Repository;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Box extends DiagramElement {
  public static final int DEFAULT_SIZE = 100;
  public static final Map<String, Color> CONNECTION_TYPE_TO_COLOR = Map.of(
              "Aggregation", new Color(0x264653),
              "Composition", new Color(0x2a9d8f),
              "Association", new Color(0xe9c46a),
              "Inheritance", new Color(0xf4a261),
              "Realization", new Color(0xe76f51)
  );

  private String name;
  private ArrayList<BoxConnection> connections;

  public Box(int x, int y, int w, int h, String name) {
    super(x, y, w, h);
    this.name = name;
    this.connections = new ArrayList<>();
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(new Color(0x53585e));
    // Draw box
    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

    // Draw name
    g.setColor(Color.WHITE);
    g.setFont(new Font("Arial", Font.PLAIN, 12));
    g.drawString(this.name, bounds.x + 10, (int) bounds.getCenterY());

    // Draw connection
    for (BoxConnection connection : this.connections) {
      ((Graphics2D) g).setStroke(new BasicStroke(3));
      g.setColor(CONNECTION_TYPE_TO_COLOR.getOrDefault(connection.type(), Color.BLACK));
      Box box = connection.to();
      g.drawLine((int) bounds.getCenterX(), (int) bounds.getCenterY(),
              (int) box.bounds.getCenterX(), (int) box.bounds.getCenterY());
    }
  }

  @Override
  public DiagramElement occupies(int x, int y) {
    if (this.bounds.contains(x, y)) {
      return this;
    }
    return null;
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.setName(name);
    if (description.getType() == null) {
      description.setType(ClassDescription.CLASS);
    }

    if (!connections.isEmpty()) {
      for (int i = 0; i < connections.size(); i++) {
        String connectionType = connections.get(i).type();

        if (connectionType.equals("Aggregation") || connectionType.equals("Composition")) {
          description.addVariable(String.format(
              "private %s %s%d", connections.get(i).to().getName(),
              connections.get(i).to().getName().toLowerCase().concat("_"), i
          ));
        }

        else if (connectionType.equals("Inheritance")) {
          description.setExtension(connections.get(i).to().getName());
        }

        else if (connectionType.equals("Realization")) {
          description.addImplementation(connections.get(i).to().getName());
        }
      }
    }

    return description;
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

  @Override
  public String toString() {
    return String.format(
        "%s centered on (%d, %d)", name, (int) bounds.getCenterX(), (int) bounds.getCenterY()
    );
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  public ArrayList<BoxConnection> getConnections() {
    return connections;
  }

}
