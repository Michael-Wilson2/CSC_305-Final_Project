package wilson.DiagramElements;
import wilson.Emojis;

import java.awt.*;

public class Decoratable extends BoxDecorator {
  public Decoratable(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.SPARKLE_EMOJI, g);
  }

  @Override
  public void addConnection(DiagramElement connection) {
    BoxDecorator decoratorConnection = (BoxDecorator) connection;
    if (decoratorConnection instanceof Decoration) {
      connections.add(decoratorConnection);
    }
  }
}
