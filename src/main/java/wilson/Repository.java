package wilson;

import java.beans.PropertyChangeSupport;
import java.util.List;
import java.util.ArrayList;

public class Repository extends PropertyChangeSupport {
  private static Repository instance;

  private List<DiagramElement> elements;
  private DiagramElement selectedElement;

  private Repository() {
    super(new Object());

    this.elements = new ArrayList<>();
    this.selectedElement = null;
  }

  public void add(DiagramElement element) {
    this.elements.add(element);
    firePropertyChange("repaint", null, 1);
  }

  public void remove(DiagramElement element) {
    this.elements.remove(element);
    firePropertyChange("repaint", null, 1);
  }

  public boolean isOccupied(int x, int y) {
    for (DiagramElement element : this.elements) {
      if (element.occupies(x, y)) {
        return true;
      }
    }
    return false;
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

  public DiagramElement getSelectedElement() {
    return this.selectedElement;
  }

  public void setSelectedElement(DiagramElement element) {
    this.selectedElement = element;
  }
}
