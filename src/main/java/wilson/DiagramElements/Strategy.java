package wilson.DiagramElements;
import wilson.Emojis;

import java.awt.*;

public class Strategy extends BoxDecorator {
  public Strategy(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.BRAIN_EMOJI, g);
  }

  @Override
  public void addConnection(DiagramElement connection) {
    // TODO: Maybe we should have "StrategyInterface" and "StrategyImpl" or similar
  }
}
