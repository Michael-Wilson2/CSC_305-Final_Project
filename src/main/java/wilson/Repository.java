package wilson;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.ArrayList;

public class Repository extends PropertyChangeSupport {
  private static Repository instance;

  private List<Box> elements;
  private Box selectedElement;
  private JFrame frame;

  private Repository() {
    super(new Object());

    this.elements = new ArrayList<>();
    this.selectedElement = null;
  }

  public void add(Box element) {
    this.elements.add(element);
    repaint();
  }

  public void remove(Box element) {
    this.elements.remove(element);
    repaint();
  }

  public Box getElementAtLocation(int x, int y) {
    for (Box element : this.elements) {
      if (element.occupies(x, y)) {
        return element;
      }
    }
    return null;
  }

  public static Repository getInstance() {
    if (instance == null) {
      instance = new Repository();
    }
    return instance;
  }

  public List<Box> getElements() {
    return this.elements;
  }

  public int size() {
    return this.elements.size();
  }

  public DiagramElement getSelectedElement() {
    return this.selectedElement;
  }

  public void setSelectedElement(Box element) {
    this.selectedElement = element;
  }

  public void setFrame(JFrame frame) {
    this.frame = frame;
  }

  public Component getFrame() {
    return this.frame;
  }

  public void repaint() {
    firePropertyChange("repaint", null, 1);
  }

  public void setBoxName(Box box, String newName) {
    box.setName(newName);
    repaint();
  }

  public void setBoxPosition(Box box, int x, int y) {
    box.setPosition(x, y);
    repaint();
  }
}
