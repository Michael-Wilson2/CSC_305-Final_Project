package diagram.BoxDecorators;
import code.ClassDescription;
import diagram.*;

import java.awt.*;

public class ChainNode extends BoxDecorator {
  public ChainNode(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.CHAIN_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addMethod(String.format(
        "public void handleRequest() {%n" +
        "%n" +
        "};"
    ));

    return description;
  }

  @Override
  public void addConnection(DiagramElement connection) {
    // TODO: Log this is impossible
  }
}
