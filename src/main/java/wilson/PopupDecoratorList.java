package wilson;

import wilson.BoxDecorators.*;

import javax.swing.*;

public class PopupDecoratorList extends JPopupMenu {

  public PopupDecoratorList(Box box, int x, int y) {
    String[] menuItems = {"observer", "observable", "singleton", "decoration", "decoratable", "chain member", "strategy", "factory", "product"};
    Class<?>[] classes = {Observer.class, Observable.class, Singleton.class, Decoration.class, Decoratable.class, ChainNode.class, Strategy.class, Factory.class, Product.class};

    for (int i = 0; i < menuItems.length; i++) {
      JMenuItem menuItem = new JMenuItem(menuItems[i]);
      add(menuItem);

      int finalI = i;
      menuItem.addActionListener(e -> {
        try {
          BoxDecorator boxDecorator = (BoxDecorator) classes[finalI].getDeclaredConstructor(int.class, int.class, Box.class).newInstance(x, y, box);
          Repository.getInstance().addDecoratorToBox(box, boxDecorator);
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      });
    }
  }
}
