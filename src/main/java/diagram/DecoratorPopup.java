package diagram;

import diagram.DiagramElements.*;

import javax.swing.*;

public class DecoratorPopup extends JPopupMenu {
  private static final String[] MENU_ITEMS = {
          "Observer", "Observable", "Singleton", "Decoration", "Decoratable",
          "Chain Member", "Strategy", "Factory", "Product"};
  private static final Class<?>[] CLASSES = {
          Observer.class, Observable.class, Singleton.class, Decoration.class, Decoratable.class,
          ChainNode.class, Strategy.class, Factory.class, Product.class};

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
          ex.printStackTrace();
        }
      });
    }
  }
}
