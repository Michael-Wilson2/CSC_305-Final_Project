package diagram;

import code.CodeCreator;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PanelCode extends JPanel implements PropertyChangeListener {
  private final JTree fileTree;
  private final DefaultMutableTreeNode rootNode;
  private final JTextPane codePane;
  private final StyledDocument doc;


  public PanelCode() {
    setBackground(Color.DARK_GRAY);

    // File tree
    this.rootNode = new DefaultMutableTreeNode("src");
    this.fileTree = new JTree(rootNode);
    JScrollPane scrollPane = new JScrollPane(fileTree);

    // Code pane
    this.codePane = new JTextPane();
    this.doc = codePane.getStyledDocument();

    // File tree + text pane
    setLayout(new GridLayout(1, 2));
    add(scrollPane);
    add(codePane);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    // update file tree TODO: use decorator instead of loop
    rootNode.removeAllChildren();
    for (Box box : Repository.getInstance().getBoxes()) {
      rootNode.add(new DefaultMutableTreeNode(box.getName()));
    }

    // update code
    try {
      doc.remove(0, doc.getLength());
    } catch (BadLocationException e) {
      System.out.println("unexpected error - BadLocationException occurred");
    }
    SimpleAttributeSet attributes = new SimpleAttributeSet();
    StyleConstants.setForeground(attributes, Color.BLACK);

    CodeCreator codeCreator = new CodeCreator();
    // TODO: need to select a file, then code will be displayed
//    doc.insertString(codeCreator.createCode());
  }


  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("repaint")) {
      repaint();
    }
  }
}
