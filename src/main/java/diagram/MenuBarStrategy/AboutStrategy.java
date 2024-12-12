package diagram.MenuBarStrategy;

import diagram.DiagramElements.Box;
import diagram.Repository;

import javax.swing.*;
import java.awt.*;

public class AboutStrategy implements MenuBarStrategy {
  @Override
  public void executeMenuAction(String itemName) {
    String body = "<html><body><p>"
                    + "<b>About</b><br/><br/>"
                    + "Box Connection color code:<br/>";

    for (String type : Box.CONNECTION_TYPE_TO_COLOR.keySet()) {
      Color color = Box.CONNECTION_TYPE_TO_COLOR.get(type);
      body += "<span style=\"color: rgb("
              + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");\">" + type + "</span><br/>";
    }

    body += "<br/>CSC 305 Final Project<br/>"
            + "Made by Michael Wilson and Andrew Kulakovsky<br/>"
            + "</p></body></html>";

    System.out.println(body);

    JOptionPane.showMessageDialog(Repository.getInstance().getFrame(), body, "About", JOptionPane.INFORMATION_MESSAGE);
  }
}
