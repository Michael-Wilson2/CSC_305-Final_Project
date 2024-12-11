package wilson.DiagramElements;
import wilson.Emojis;

import java.awt.*;

public class Singleton extends BoxDecorator {
  public Singleton(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.CROWN_EMOJI, g);
  }

  @Override
  public void addConnection(DiagramElement connection) {
    // TODO: Log this is impossible
  }
}
