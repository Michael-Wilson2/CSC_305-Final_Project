package project;

import project.diagram.DiagramElements.Box;
import project.diagram.DiagramElements.BoxDecorator;
import project.diagram.DiagramElements.DiagramElement;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

/** A singleton repository for all the data in the project.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */
public class Repository extends PropertyChangeSupport {
  private static Repository instance;

  private List<DiagramElement> elements;
  private JFrame frame;
  private BoxDecorator connectingDecorator;
  private Box connectionBox;
  private String connectorName;
  private Point lineStart;
  private Point pointer;
  private Point pointerDelta;
  private DiagramElement selectedElement;
  private DiagramElement selectedRootElement;
  private String filePath;
  private Logger logger = LoggerFactory.getLogger(Repository.class);

  private Repository() {
    super(new Object());

    this.elements = new ArrayList<>();
    this.frame = null;
    this.connectingDecorator = null;
    this.connectionBox = null;
    this.selectedElement = null;
    this.pointer = new Point(0, 0);
    selectedRootElement = null;
  }

  public void add(Box element) {
    this.elements.add(element);
    logger.info(String.format("a box has been added to the Repository at (%d, %d)",
        (int) element.getBounds().getCenterX(), (int) element.getBounds().getCenterY()));
    repaint();
  }

  public void remove(Box element) {
    this.elements.remove(element);
    logger.info(String.format("%s has been removed from the repository", element));
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

  public List<String> getBoxNames() {
    List<String> boxNames = new ArrayList<>();

    for (DiagramElement element : elements) {
      boxNames.add(element.getBox().getName());
    }

    return boxNames;
  }

  public Box getBox(String name) {
    for (int i = 0; i < elements.size(); i++) {
      if (elements.get(i).getBox().getName().equals(name)) {
        return elements.get(i).getBox();
      }
    }
    return null;
  }

  public DiagramElement getFirstElement(String name) {
    for (DiagramElement element : elements) {
      if (element.getBox().getName().equals(name)) {
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

  public void setBoxName(Box box, String newName) {
    box.setName(newName);
    repaint();
  }

  public void setBoxPosition(Box box, int x, int y) {
    box.setPosition(x, y);
    repaint();
  }

  public void addElementToDecorator(DiagramElement diagramElement, BoxDecorator boxDecorator) {
    boxDecorator.add(diagramElement);
    elements.remove(diagramElement);
    elements.add(boxDecorator);
    logger.info(String.format("%s has been added to %s", diagramElement, boxDecorator));
    repaint();
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
    this.connectorName = itemName;
  }

  public String getConnector() {
    return connectorName;
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

  public void saveDiagramElements(String filename) {
    File file = new File(filename);

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
      oos.writeObject(elements);
    } catch (IOException e) {
      logger.error("unable to save diagram elements");
    }
  }

  public void loadDiagramElements(String filename) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      elements = (List<DiagramElement>) ois.readObject();
      repaint();
    } catch (Exception e) {
      logger.error("unable to load diagram elements");
    }
  }

  public void resetDiagram() {
    elements.clear();
    repaint();
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getFilePath() {
    return this.filePath;
  }
}
