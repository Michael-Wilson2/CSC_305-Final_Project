package wilson.BoxDecorators;
import wilson.BoxDecorator;

import java.awt.*;

public class Singleton extends BoxDecorator {
  public Singleton(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);


  }
}
