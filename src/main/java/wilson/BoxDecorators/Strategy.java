package wilson.BoxDecorators;
import wilson.Box;
import wilson.BoxDecorator;
import wilson.Emojis;

import java.awt.*;

public class Strategy extends BoxDecorator {
  public Strategy(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    g.drawString(Emojis.BRAIN_EMOJI, getRelativeX(), getRelativeY());
  }
}
