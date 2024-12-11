package code.skeletons;

import java.beans.PropertyChangeSupport;

public class Observable extends PropertyChangeSupport {
  public Observable() {
    super(new Object());
  }
}
