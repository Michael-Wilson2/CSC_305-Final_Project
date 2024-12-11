package wilson.DiagramElements;
import code.ClassDescription;
import code.ClassDescriptionFactory;
import wilson.Emojis;

import java.awt.*;

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
    ClassDescriptionFactory factoryDescription = new ClassDescriptionFactory(description);
//    factoryDescription.setProductName(); // don't have it yet... do in Box implementation of updateDescription

    if (diagramElement != null) {
      return diagramElement.updateDescription(description);
    } else {
      return description;
    }
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Factory) {
      connections.add(decoratorConnection);
    }
  }
}
