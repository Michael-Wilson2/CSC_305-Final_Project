package wilson.DiagramElements;
import wilson.Emojis;

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
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Factory) {
      connections.add(decoratorConnection);
    }
  }
}
