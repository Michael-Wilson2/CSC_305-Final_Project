package diagram.DiagramElements;
import code.ClassDescription;
import code.CodeCreator;
import diagram.Emojis;

import java.awt.*;

public class Decoration extends BoxDecorator {
  public Decoration(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.PALETTE_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addVariable(String.format(
        "protected %s next;", "<Component>" // TODO: get Component name from diagram
    ));

    description.addMethod(String.format(
        "public void add(%s next) {%n" +
            CodeCreator.TAB + "this.next = next;%n" +
            "}", "<Component>"
    ));

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
    if (decoratorConnection instanceof Decoratable) {
      connections.add(decoratorConnection);
    }
  }
}
