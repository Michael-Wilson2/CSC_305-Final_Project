package diagram.BoxDecorators;
import code.ClassDescription;
import diagram.Box;
import diagram.BoxDecorator;
import diagram.DiagramElement;
import diagram.Emojis;

import java.awt.*;

public class Singleton extends BoxDecorator {
  public Singleton(int w, int h, Box box) {
    super(w, h, box);
  }

  @Override
  public void draw(Graphics g) {
    super.draw(g);
    drawEmoji(Emojis.CROWN_EMOJI, g);
  }

  @Override
  public ClassDescription updateDescription(ClassDescription description) {
    description.addVariable("private static Singleton instance;");

    description.setConstructorAccess(ClassDescription.PRIVATE);

    description.addMethod(String.format(
        "public static %s getInstance() {%n" +
        "  if (instance == null) {%n" +
        "    instance = new %s();%n" +
        "  }%n" +
        "  return instance;%n" +
        "}", "<Singleton>", "<Singleton>" // TODO: use name of class
    ));

    return description;
  }

  @Override
  public void addConnection(DiagramElement connection) {
    // TODO: Log this is impossible
  }
}
