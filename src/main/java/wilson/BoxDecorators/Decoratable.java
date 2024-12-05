package wilson.BoxDecorators;
import wilson.BoxDecorator;

import java.awt.*;

public class Decoratable extends BoxDecorator {
  public Decoratable(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);


  }
}
