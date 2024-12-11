package code.skeletons;

// Additions to code:
// make class abstract
// class must extend the decoratable (top leve) component
// insert the following method(s):
//  public void add(Decoratable decoratable) {
//    next = decoratable;
//  }
//
//  @Override
//  public void my_function() {
//
//  }

public abstract class Decorator extends Decoratable {
  protected Decoratable next;

  public void add(Decoratable decoratable) {
    next = decoratable;
  }

  @Override
  public void my_function() {

  }
}
