package wilson.DiagramElements;
import code.ClassDescription;
import wilson.*;

import java.awt.*;

public class ChainNode extends BoxDecorator {
  public ChainNode(int w, int h) {
    super(w, h);
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

    if (diagramElement != null) {
      return diagramElement.updateDescription(description);
    } else {
      return description;
    }
  }

  @Override
  public void addConnection(DiagramElement connection) {
    // TODO: Log this is impossible
  }
}
