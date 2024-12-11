package wilson;

import wilson.DiagramElements.Box;
import wilson.DiagramElements.BoxDecorator;
import wilson.DiagramElements.DiagramElement;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.ArrayList;

public class Repository extends PropertyChangeSupport {
  private static Repository instance;

  private List<DiagramElement> elements;
  private JFrame frame;
  private boolean isConnectingDecorator;
  private BoxDecorator connectingDecorator;
  private wilson.DiagramElements.Box connectionBox;
  private String itemName;
  private Point lineStart;
  private Point pointer;
  private Point pointerDelta;
  private DiagramElement selectedElement;
  private DiagramElement selectedRootElement;

  private Repository() {
    super(new Object());

    this.elements = new ArrayList<>();
    this.frame = null;
    this.isConnectingDecorator = false;
    this.connectingDecorator = null;
    this.connectionBox = null;
    this.selectedElement = null;
    this.pointer = new Point(0, 0);
    selectedRootElement = null;
  }

  public void add(Box element) {
    this.elements.add(element);
    repaint();
  }

  public void remove(wilson.DiagramElements.Box element) {
    this.elements.remove(element);
    repaint();
  }

  public DiagramElement getElementAtLocation(int x, int y) {
    for (DiagramElement element : this.elements) {
      DiagramElement occupyingElement = element.occupies(x, y);
      if (occupyingElement != null) {
        return occupyingElement;
      }
    }
    return null;
  }

  public DiagramElement getRootElementAtLocation(int x, int y) {
    for (DiagramElement element : this.elements) {
      DiagramElement occupyingElement = element.occupies(x, y);
      if (occupyingElement != null) {
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

  public List<DiagramElement> getElements() {
    return this.elements;
  }

  public int size() {
    return this.elements.size();
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

  public void setBoxName(wilson.DiagramElements.Box box, String newName) {
    box.setName(newName);
    repaint();
  }

  public void setBoxPosition(wilson.DiagramElements.Box box, int x, int y) {
    box.setPosition(x, y);
    repaint();
  }

  public void addElementToDecorator(DiagramElement diagramElement, BoxDecorator boxDecorator) {
    boxDecorator.add(diagramElement);
    elements.remove(diagramElement);
    elements.add(boxDecorator);
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

  public wilson.DiagramElements.Box getConnectingBox() {
    return connectionBox;
  }

  public void setConnectingBox(wilson.DiagramElements.Box connectingBox) {
    this.connectionBox = connectingBox;
  }

  public void setConnector(String itemName) {
    this.itemName = itemName;
  }

  public String getConnector() {
    return itemName;
  }

  public DiagramElement getSelectedElement() {
    return selectedElement;
  }

  public void setSelectedElement(DiagramElement selectedElement) {
    this.selectedElement = selectedElement;
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
    this.pointerDelta = new Point(this.pointer.x - x, this.pointer.y - y);
    this.pointer = new Point(x, y);
  }

  public Point getPointerDelta() {
    return this.pointerDelta;
  }

  public DiagramElement getSelectedRootElement() {
    return this.selectedRootElement;
  }

  public void setSelectedRootElement(DiagramElement element) {
    this.selectedRootElement = element;
  }

  public void moveElement(DiagramElement element) {
    element.move(pointerDelta);
    repaint();
  }
}
