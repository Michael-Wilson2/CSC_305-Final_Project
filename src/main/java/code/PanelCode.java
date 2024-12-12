package code;

import diagram.DiagramElements.Box;
import diagram.Repository;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/** A JPanel to show the files in a tree format alongside the code corresponding to the user's diagram
 * @author Michael Wilson
 * @version 1.0
 */
public class PanelCode extends JPanel implements PropertyChangeListener, MouseListener {
  private final JTree fileTree;
  private final DefaultTreeModel treeModel;
  private final DefaultMutableTreeNode rootNode;
  private final JTextPane codePane;
  private final StyledDocument doc;
  private String selectedPath;
  private Logger logger = LoggerFactory.getLogger(PanelCode.class);

  public PanelCode() {
    setBackground(Color.DARK_GRAY);

    // File tree
    this.rootNode = new DefaultMutableTreeNode("src");
    this.treeModel = new DefaultTreeModel(rootNode);
    this.fileTree = new JTree(treeModel);
    fileTree.setFont(new Font("Calibri", Font.PLAIN, 26));
    fileTree.addMouseListener(this);
    JScrollPane scrollPane = new JScrollPane(fileTree);

    // Code pane
    this.codePane = new JTextPane();
    this.doc = codePane.getStyledDocument();
    codePane.setEditable(false);
    codePane.getCaret().setVisible(false);
    codePane.setFont(new Font("Courier", Font.PLAIN, 16));

    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weighty = 1;
    gbc.weightx = 0.33;
    add(scrollPane, gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weighty = 1;
    gbc.weightx = 0.67;
    add(codePane, gbc);

    selectedPath = null;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // update file tree
    rootNode.removeAllChildren();
    for (String boxName : Repository.getInstance().getBoxNames()) {
      rootNode.add(new DefaultMutableTreeNode(boxName));
    }
    treeModel.reload();

    // update code
    try {
      doc.remove(0, doc.getLength());

      SimpleAttributeSet attributes = new SimpleAttributeSet();
      StyleConstants.setForeground(attributes, Color.BLACK);

      CodeCreator codeCreator = new CodeCreator();
      Box desiredBox = Repository.getInstance().getBox(selectedPath);
      if (desiredBox != null) {
        String code = codeCreator.createCode(Repository.getInstance().getFirstElement(selectedPath));
        doc.insertString(
            doc.getLength(),
            code,
            attributes
        );
      }
    } catch (BadLocationException e) {
      logger.error("BadLocationException occurred when trying to update code contents");
    }
  }

  private void setSelectedPath(String selectedPath) {
    this.selectedPath = selectedPath;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("repaint")) {
      repaint();
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    String filename = fileTree.getPathForLocation(e.getX(), e.getY()).getLastPathComponent().toString();
    setSelectedPath(filename);
    logger.trace(String.format("user is viewing code for %s", filename));
    repaint();
  }

  // unused methods below -----------------------------------------------------

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}
}
