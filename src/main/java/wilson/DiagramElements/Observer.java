package wilson.DiagramElements;
import wilson.Emojis;

import java.awt.*;

public class Observer extends BoxDecorator {
  public Observer(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.EYES_EMOJI, g);
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Observable) {
      connections.add(decoratorConnection);
    }
  }
}
