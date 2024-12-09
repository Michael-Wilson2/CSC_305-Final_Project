package wilson.BoxDecorators;
import wilson.Box;
import wilson.BoxDecorator;

import java.awt.*;

public class Observable extends BoxDecorator {
  public Observable(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
  }
}
