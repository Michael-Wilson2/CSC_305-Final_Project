package wilson.BoxDecorators;
import wilson.Box;
import wilson.BoxDecorator;

import java.awt.*;

public class Product extends BoxDecorator {
  public Product(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
  }
}
