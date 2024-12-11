package diagram.DiagramElements;
import code.ClassDescription;
import diagram.Emojis;

import java.awt.*;

public class Decoratable extends BoxDecorator {
  public Decoratable(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.SPARKLE_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addMethod(String.format(
        "@Override%n" +
        "public void operation() {%n" +
        "%n" +
        "}"
    ));

    if (diagramElement != null) {
      return diagramElement.updateDescription(description);
    } else {
      return description;
    }
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Decoration) {
      connections.add(decoratorConnection);
    }
  }
}
