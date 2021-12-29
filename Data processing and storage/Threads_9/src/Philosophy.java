import java.util.Random;

public class Philosophy {

  public static void main(String[] args) {
    Random random = new Random();
    Object[] forks = new Object[5];
    Thread[] philosophers = new Thread[5];
    for (int i = 0; i < 5; i++) {
      forks[i] = new Object();
    }
    for (int i = 0; i < 5; i++) {
      int finalI = i;
      philosophers[i] = new Thread(() -> {
        System.out.println("philosopher" + finalI + " is thinking");
        try {
          Thread.sleep(random.nextInt(5) * 1000);
        } catch (InterruptedException e) {
          return;
        }
        synchronized (forks[finalI]) {
          System.out.println("philosopher " + finalI + " grab fork" + finalI);
          synchronized (forks[(finalI + 1) % 5]) {
            System.out.println("philosopher " + finalI + " grab fork" + (finalI + 1) % 5);
            System.out.println("philosopher " + finalI + " is eating");
            try {
              Thread.sleep(random.nextInt(5) * 1000);
            } catch (InterruptedException e) {
              return;
            }
            System.out.println("philosopher " + finalI + " finished eating");
          }
        }
      });
    }
    for (Thread t : philosophers) {
      t.start();
    }
  }
}