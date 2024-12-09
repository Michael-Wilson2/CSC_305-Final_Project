package wilson.BoxDecorators;
import wilson.Box;
import wilson.BoxDecorator;
import wilson.Emojis;

import java.awt.*;

public class Observer extends BoxDecorator {
  public Observer(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    g.drawString(Emojis.EYES_EMOJI, getRelativeX(), getRelativeY());
  }
}
