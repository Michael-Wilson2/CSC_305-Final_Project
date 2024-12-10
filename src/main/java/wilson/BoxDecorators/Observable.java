package wilson.BoxDecorators;
import wilson.Box;
import wilson.BoxDecorator;
import wilson.DiagramElement;
import wilson.Emojis;

import java.awt.*;

public class Observable extends BoxDecorator {
  public Observable(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.GLOBE_EMOJI, g);
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Observer) {
      connections.add(decoratorConnection);
    }
  }
}
