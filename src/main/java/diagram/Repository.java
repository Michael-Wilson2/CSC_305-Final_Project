package diagram;

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
  private boolean isConnectingDecorator;
  private BoxDecorator connectingDecorator;
  private Box connectionBox;
  private String itemName;
  private Box selectedBox;
  private Point lineStart;
  private Point pointer;

  private Repository() {
    super(new Object());

    this.elements = new ArrayList<>();
    this.selectedElement = null;
    this.frame = null;
    this.isConnectingDecorator = false;
    this.connectingDecorator = null;
    this.connectionBox = null;
    this.selectedBox = null;
    this.pointer = null;
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

  public List<Box> getBoxes() {
    return this.elements;
  }

  public Box getBox(String name) {
    for (int i = 0; i < elements.size(); i++) {
      if (elements.get(i).getName().equals(name)) {
        return elements.get(i); // TODO: after refactoring decorator, do .getBox() or similar
      }
    }
    return null;
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

  public JFrame getFrame() {
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

  public void addDecoratorToBox(Box box, BoxDecorator boxDecorator) {
    box.addDecorator(boxDecorator);
    repaint();
  }

  public boolean getIsConnectingDecorator() {
    return isConnectingDecorator;
  }

  public void setIsConnectingDecorator(boolean isConnectingDecorator) {
    this.isConnectingDecorator = isConnectingDecorator;
  }

  public BoxDecorator getConnectingDecorator() {
    return connectingDecorator;
  }

  public void setConnectingDecorator(BoxDecorator connectingDecorator) {
    this.connectingDecorator = connectingDecorator;
  }

  public Box getConnectingBox() {
    return connectionBox;
  }

  public void setConnectingBox(Box connectingBox) {
    this.connectionBox = connectingBox;
  }

  public void setConnector(String itemName) {
    this.itemName = itemName;
  }

  public String getConnector() {
    return itemName;
  }

  public Box getSelectedBox() {
    return selectedBox;
  }

  public void setSelectedBox(Box selectedBox) {
    this.selectedBox = selectedBox;
  }

  public Point getLineStart() {
    return lineStart;
  }

  public void setLineStart(Point lineStart) {
    this.lineStart = lineStart;
  }

  public Point getPointer() {
    return pointer;
  }

  public void setPointer(int x, int y) {
    this.pointer = new Point(x, y);
  }
}
