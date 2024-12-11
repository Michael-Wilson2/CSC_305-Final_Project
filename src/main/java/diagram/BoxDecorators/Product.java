package diagram.BoxDecorators;
import code.ClassDescription;
import code.ClassDescriptionFactory;
import diagram.Box;
import diagram.BoxDecorator;
import diagram.DiagramElement;
import diagram.Emojis;

import java.awt.*;

public class Product extends BoxDecorator {
  public Product(int w, int h, Box box) {
    super(w, h, box);
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

    return factoryDescription;
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Factory) {
      connections.add(decoratorConnection);
    }
  }
}