package diagram.BoxDecorators;
import code.ClassDescription;
import diagram.Box;
import diagram.BoxDecorator;
import diagram.DiagramElement;
import diagram.Emojis;

import java.awt.*;

public class Decoratable extends BoxDecorator {
  public Decoratable(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.SPARKLE_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addMethod(String.format(
        "public void operation() {%n" +
        "%n" +
        "}"
    ));

    return description;
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Decoration) {
      connections.add(decoratorConnection);
    }
  }
}
