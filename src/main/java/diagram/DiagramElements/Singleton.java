package diagram.DiagramElements;
import code.ClassDescription;
import code.CodeCreator;

import java.awt.*;

public class Singleton extends BoxDecorator {
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
    // TODO: Log this is impossible
  }
}
