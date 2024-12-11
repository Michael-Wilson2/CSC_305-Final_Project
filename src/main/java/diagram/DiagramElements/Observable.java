package diagram.DiagramElements;
import code.ClassDescription;
import diagram.Emojis;

import java.awt.*;

public class Observable extends BoxDecorator {
  public Observable(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.GLOBE_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addImport("java.beans.PropertyChangeSupport");
    description.setExtension("PropertyChangeSupport");
    description.setConstructorBody(String.format(
        "super(new Object());%n"
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
    if (decoratorConnection instanceof Observer) {
      connections.add(decoratorConnection);
    }
  }
}
