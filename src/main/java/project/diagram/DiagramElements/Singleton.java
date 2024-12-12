package project.diagram.DiagramElements;
import project.code.ClassDescription;
import project.code.CodeCreator;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.awt.*;

/** A box decoration for visually representing a singleton class.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class Singleton extends BoxDecorator {
  private Logger logger = LoggerFactory.getLogger(Singleton.class);

  public Singleton(int w, int h) {
    super(w, h);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.CROWN_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addVariable("private static " + CodeCreator.BOX_NAME_PLACEHOLDER + " instance;");

    description.setConstructorAccess(ClassDescription.PRIVATE);

    description.addMethod(String.format(
        "public static %s getInstance() {%n" +
        CodeCreator.TAB + "if (instance == null) {%n" +
        CodeCreator.TAB + CodeCreator.TAB + "instance = new %s();%n" +
        CodeCreator.TAB + "}%n" +
        CodeCreator.TAB + "return instance;%n" +
        "}", CodeCreator.BOX_NAME_PLACEHOLDER, CodeCreator.BOX_NAME_PLACEHOLDER
    ));

    if (diagramElement != null) {
      return diagramElement.updateDescription(description);
    } else {
      return description;
    }
  }

  @Override
  public void addConnection(DiagramElement connection) {
    logger.warn("adding a connection to a singleton decoration is not allowed");
  }
}
