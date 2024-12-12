package project.diagram.MenuBarStrategy;

import project.Repository;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/** Extension of JFileChooser for choosing .dag files to open/save.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class FileChooser extends JFileChooser {
  public FileChooser(String text) {
    setDialogTitle(text);
    setFileFilter(new FileNameExtensionFilter("DIG Files", "dig"));
    setAcceptAllFileFilterUsed(false);
  }

  public String getSaveFilePath() {
    int userSelection = showSaveDialog(Repository.getInstance().getFrame());
    if (userSelection != JFileChooser.APPROVE_OPTION) {
      return null;
    }

    String path = getSelectedFile().getAbsolutePath();
    if (!path.toLowerCase().endsWith(".dig")) {
      path += ".dig";
    }
    return path;
  }

  public String getOpenFilePath() {
    int userSelection = showOpenDialog(Repository.getInstance().getFrame());
    if (userSelection != JFileChooser.APPROVE_OPTION) {
      return null;
    }

    String path = getSelectedFile().getAbsolutePath();
    if (!path.toLowerCase().endsWith(".dig")) {
      path += ".dig";
    }
    return path;
  }
}
