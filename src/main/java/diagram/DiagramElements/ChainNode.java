package diagram.DiagramElements;
import code.ClassDescription;
import code.CodeCreator;

import java.awt.*;

/** A box decoration for visually representing a chain node class.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class ChainNode extends BoxDecorator {
  public ChainNode(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.CHAIN_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addVariable(String.format("protected %s successor;", CodeCreator.HANDLER_NAME_PLACEHOLDER));

    description.addMethod(String.format(
        "public void setSuccessor(%s successor) {%n" +
        CodeCreator.TAB + "this.successor = successor;%n" +
        "}", CodeCreator.HANDLER_NAME_PLACEHOLDER
    ));

    description.addMethod(String.format(
        "public void handleRequest() {%n" +
        "%n" +
        "};"
    ));

    if (diagramElement != null) {
      return diagramElement.updateDescription(description);
    } else {
      return description;
    }
  }

  @Override
  public void addConnection(DiagramElement connection) {
    // TODO: Log this is impossible
  }
}
