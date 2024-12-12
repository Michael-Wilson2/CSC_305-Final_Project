package diagram.MenuBarStrategy;

import diagram.Repository;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
