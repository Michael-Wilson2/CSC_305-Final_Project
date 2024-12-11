package wilson.DiagramElements;
import wilson.*;

import java.awt.*;

public class ChainNode extends BoxDecorator {
  public ChainNode(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.CHAIN_EMOJI, g);
  }

  @Override
  public void addConnection(DiagramElement connection) {
    // TODO: Log this is impossible
  }
}
