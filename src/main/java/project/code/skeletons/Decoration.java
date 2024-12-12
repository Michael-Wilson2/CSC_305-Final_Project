package project.code.skeletons;

public class Decoration extends Decoratable {
  protected Object next; // should be top leve component, not just any object

  public void add(Object decoratable) {
    next = decoratable;
  }

  @Override
  public void operation() {

  }
}
