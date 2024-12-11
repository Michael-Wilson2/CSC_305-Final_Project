package wilson.BoxDecorators;
import code.ClassDescription;
import wilson.Box;
import wilson.BoxDecorator;
import wilson.DiagramElement;
import wilson.Emojis;

import java.awt.*;

public class Decoration extends BoxDecorator {
  public Decoration(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.PALETTE_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addVariable(String.format(
        "protected %s next;", "<Decoratable>" // TODO: make this use the name of the decoratable class
    ));

    description.addMethod(String.format(
        "public void add(Decoratable decoratable) {%n" +
        "  next = decoratable;%n" +
        "}"
    ));

    description.addMethod(String.format(
        "@Override%n" +
        "public void operation() {%n" +
        "%n" +
        "}"
    ));

    return description;
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Decoratable) {
      connections.add(decoratorConnection);
    }
  }
}
