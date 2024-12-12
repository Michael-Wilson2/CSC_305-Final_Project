package diagram.DiagramElements;
import code.ClassDescription;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.awt.*;

/** A box decoration for visually representing a strategy class.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class Strategy extends BoxDecorator {
  private Logger logger = LoggerFactory.getLogger(Strategy.class);

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
    logger.warn("adding connections to chain members is not allowed");
  }
}
