package diagram.MenuBarStrategy;

import diagram.Repository;

import javax.swing.*;

public class AboutStrategy implements MenuBarStrategy {
  @Override
  public void executeMenuAction(String itemName) {
    JOptionPane.showMessageDialog(Repository.getInstance().getFrame(),
            "<html><body><p>"
                    + "<b>About</b><br/><br/>"
                    + "CSC 305 Final Project<br/>"
                    + "Made by Michael Wilson and Andrew Kulakovsky<br/>"
                    + "</p></body></html>",
            "About", JOptionPane.INFORMATION_MESSAGE);
  }
}
