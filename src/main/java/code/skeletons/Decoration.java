package code.skeletons;

public class Decoration extends Decoratable {
  protected Decoratable next;

  public void add(Decoratable decoratable) {
    next = decoratable;
  }

  @Override
  public void operation() {

  }
}
