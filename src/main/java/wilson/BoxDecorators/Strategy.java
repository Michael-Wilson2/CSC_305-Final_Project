package wilson.BoxDecorators;
import wilson.Box;
import wilson.BoxDecorator;

import java.awt.*;

public class Strategy extends BoxDecorator {
  public Strategy(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);


  }
}
