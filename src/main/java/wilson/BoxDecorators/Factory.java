package wilson.BoxDecorators;
import wilson.Box;
import wilson.BoxDecorator;
import wilson.DiagramElement;
import wilson.Emojis;

import java.awt.*;

public class Factory extends BoxDecorator {
  public Factory(int w, int h, Box box) {
    super(w, h, box);
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
