package diagram.DiagramElements;

public class BoxConnection {
  private final Box from;
  private final Box to;
  private final String type;

  public BoxConnection(Box from, Box to, String type) {
    this.from = from;
    this.to = to;
    this.type = type;
  }

  // TODO: draw in different colors/styles based on type?
  //  should we have subclasses instead of type?

  public Box getFrom() {
    return from;
  }

  public Box getTo() {
    return to;
  }

  public String getType() {
    return type;
  }
}
