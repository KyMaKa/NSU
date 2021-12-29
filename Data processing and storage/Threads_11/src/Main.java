import java.util.concurrent.Semaphore;

public class Main {
  public static void main(String[] args) {
    Semaphore s1 = new Semaphore(0);
    Semaphore s2 = new Semaphore(1);
    Thread child = new Thread(() -> {
              for (int i = 0; i < 10; i++) {
                try {
                  s1.acquire();
                  System.out.println("Some string from child");
                } catch (InterruptedException ignored) {
                } finally {
                  s2.release();
                }
              }
            });
    child.start();
    for (int i = 0; i < 10; i++) {
      try {
        s2.acquire();
        System.out.println("Some string from parent");
      } catch (InterruptedException ignored) {
      } finally {
        s1.release();
      }
    }
  }
}