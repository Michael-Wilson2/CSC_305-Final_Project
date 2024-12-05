package wilson.BoxDecorators;
import wilson.BoxDecorator;

import java.awt.*;

public class Observable extends BoxDecorator {
  public Observable(int x, int y, int w, int h) {
    super(x, y, w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);


  }
}
