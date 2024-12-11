package code.skeletons;

// Additions to code:
// add variable: private static Singleton instance
// make constructor private
// add method:
//  public static Singleton getInstance() {
//    if (instance == null) {
//      instance = new Singleton();
//    }
//    return instance;
//  }

public class Singleton {
  private static Singleton instance;

  private Singleton() {

  }

  public static Singleton getInstance() {
    if (instance == null) {
      instance = new Singleton();
    }
    return instance;
  }
}
