public class Main {

  public static void main(String[] args) {
    Object object = new Object();
    boolean flag = true;
    Thread child = new Thread(() -> {
      for (int i = 0; i < 10; i++) {
        synchronized (object) {
          try {
            object.wait();
          } catch (InterruptedException ignore) { }
          System.out.println("Some text string from child");
        }

        synchronized (object) {
          object.notify();
        }
      }
    });
    child.start();
    for (int i = 0; i < 10; i++) {
      if (flag)
        flag = false;
      else {
        synchronized (object) {
          try {
            object.wait();
          } catch (InterruptedException ignore) { }
        }
      }
      System.out.println("Some text string from parent");
      synchronized (object) {
        object.notify();
      }
    }
  }
}
