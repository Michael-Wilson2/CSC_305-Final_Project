package project.diagram;

import diagram.DiagramElements.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import project.Repository;
import project.diagram.DiagramElements.*;

import javax.swing.*;

/** A JPopupMenu for selecting a decorator type.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class DecoratorPopup extends JPopupMenu {
  private static final String[] MENU_ITEMS = {
          "Observer", "Observable", "Singleton", "Decoration", "Decoratable",
          "Chain Member", "Strategy", "Factory", "Product"};
  private static final Class<?>[] CLASSES = {
          Observer.class, Observable.class, Singleton.class, Decoration.class, Decoratable.class,
          ChainNode.class, Strategy.class, Factory.class, Product.class};
  private Logger logger = LoggerFactory.getLogger(DecoratorPopup.class);

  public DecoratorPopup(DiagramElement element, int x, int y) {
    for (int i = 0; i < MENU_ITEMS.length; i++) {
      JMenuItem menuItem = new JMenuItem(MENU_ITEMS[i]);
      add(menuItem);

      int finalI = i;
      menuItem.addActionListener(e -> {
        try {
          BoxDecorator boxDecorator = (BoxDecorator) CLASSES[finalI]
                  .getDeclaredConstructor(int.class, int.class)
                  .newInstance(x, y);
          Repository.getInstance().addElementToDecorator(element, boxDecorator);
        } catch (Exception ex) {
          logger.error("error creating decorator popup menu");
        }
      });
    }
  }
}
