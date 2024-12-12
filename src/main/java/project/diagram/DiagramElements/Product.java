package project.diagram.DiagramElements;
import project.code.ClassDescription;

import java.awt.*;

/** A box decoration for visually representing a product class.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class Product extends BoxDecorator {
  public Product(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.BOX_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    // no changes/additions to description; no unique code

    if (diagramElement != null) {
      return diagramElement.updateDescription(description);
    } else {
      return description;
    }
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (!connections.contains(decoratorConnection) && decoratorConnection instanceof Factory) {
      connections.add(decoratorConnection);
      decoratorConnection.addConnection(this);
    }
  }
}
