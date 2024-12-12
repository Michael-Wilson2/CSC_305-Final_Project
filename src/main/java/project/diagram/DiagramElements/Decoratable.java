package project.diagram.DiagramElements;
import project.code.ClassDescription;
import project.code.CodeCreator;

import java.awt.*;

/** A box decoration for visually representing a decoratable class.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class Decoratable extends BoxDecorator {
  public Decoratable(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(SPARKLE_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.setExtension(CodeCreator.COMPONENT_NAME_PLACEHOLDER);

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
