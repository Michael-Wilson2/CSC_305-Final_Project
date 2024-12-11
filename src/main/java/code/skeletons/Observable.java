package code.skeletons;

// Additions to code:
// import java.beans.PropertyChangeSupport
// class must extend PropertyChangeSupport
// constructor must call super(new Object())

import java.beans.PropertyChangeSupport;

public class Observable extends PropertyChangeSupport {
  public Observable() {
    super(new Object());
  }
}
