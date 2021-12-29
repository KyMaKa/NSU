import java.util.Random;

public class Main {
  public static void main(String[] args) {
    Random random = new Random();
    Object[] forks = new Object[5];
    Object fMonitor = new Object();
    Thread[] philosophers = new Thread[5];
    for (int i = 0; i < 5; i++) {
      forks[i] = new Object();
    }
    for (int i = 0; i < 5; i++) {
      int finalI = i;
      philosophers[i] =
          new Thread(
              () -> {
                while (!Thread.currentThread().isInterrupted()) {
                  System.out.println("philosopher" + finalI + " is thinking");
                  try {
                    Thread.sleep(random.nextInt(5) * 1000);
                  } catch (InterruptedException e) {
                    break;
                  }
                  synchronized (fMonitor) {
                    synchronized (forks[finalI]) {
                      System.out.println("philosopher" + finalI + " grab fork" + finalI);
                      synchronized (forks[(finalI + 1) % 5]) {
                        System.out.println("philosopher" + finalI + " grab fork" + (finalI + 1) % 5);
                        System.out.println("philosopher" + finalI + " is eating");
                        try {
                          Thread.sleep(random.nextInt(5) * 1000);
                        } catch (InterruptedException e) {
                          break;
                        }
                        System.out.println("philosopher" + finalI + " finished eating");
                      }
                    }
                  }
                }
              });
    }
    for (Thread p : philosophers) {
      p.start();
    }
    try {
      Thread.sleep(20000);
    } catch (InterruptedException ignored) {

    }
    for (Thread p : philosophers) {
      p.interrupt();
      try {
        p.join();
      } catch (InterruptedException ignored) {

      }
    }
  }
}