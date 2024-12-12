package diagram.DiagramElements;
import code.ClassDescription;

import java.awt.*;

public class Strategy extends BoxDecorator {
  public Strategy(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.BRAIN_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.setType(ClassDescription.INTERFACE);

    description.addMethod("void algorithm();");

    if (diagramElement != null) {
      return diagramElement.updateDescription(description);
    } else {
      return description;
    }
  }

  @Override
  public void addConnection(DiagramElement connection) {
    // TODO: Maybe we should have "StrategyInterface" and "StrategyImpl" or similar
  }
}
