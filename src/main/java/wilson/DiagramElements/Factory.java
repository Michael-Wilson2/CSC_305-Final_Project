package wilson.DiagramElements;
import wilson.Emojis;

import java.awt.*;

public class Factory extends BoxDecorator {
  public Factory(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.FACTORY_EMOJI, g);
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Product) {
      connections.add(decoratorConnection);
    }
  }
}
