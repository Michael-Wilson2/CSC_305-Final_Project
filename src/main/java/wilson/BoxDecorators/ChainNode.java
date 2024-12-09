package wilson.BoxDecorators;
import wilson.Box;
import wilson.BoxDecorator;
import wilson.Emojis;

import java.awt.*;

public class ChainNode extends BoxDecorator {
  public ChainNode(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    g.drawString(Emojis.CHAIN_EMOJI, getRelativeX(), getRelativeY());
  }
}
