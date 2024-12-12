package diagram.DiagramElements;
import code.ClassDescription;

import java.awt.*;

/** A box decoration for visually representing an observer class.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class Observer extends BoxDecorator {
  public Observer(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.EYES_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addImport("java.beans.PropertyChangeEvent");
    description.addImport("java.beans.PropertyChangeListener");

    description.addImplementation("PropertyChangeListener");

    description.addMethod(String.format(
        "@Override%n" +
        "public void propertyChange(PropertyChangeEvent evt) {%n" +
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
    if (decoratorConnection instanceof Observable) {
      connections.add(decoratorConnection);
    }
  }
}
