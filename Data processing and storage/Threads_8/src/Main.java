import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import sun.misc.Signal;

public class Main {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

    AtomicBoolean stop = new AtomicBoolean(false);
    int steps = 10000000;
    Scanner scanner = new Scanner(System.in);
    int threadCnt = scanner.nextInt();

    Signal.handle(new Signal("INT"), sig -> {
      System.out.println("Signal registered");
      stop.set(true);
    });

    Thread[] threads = new Thread[threadCnt];
    CalculatePi[] piCalculators = new CalculatePi[threadCnt];

    for (int i = 0; i < threadCnt; i++) {
      piCalculators[i] = new CalculatePi(i * steps / threadCnt, (i + 1) * steps / threadCnt, steps);
      threads[i] = new Thread(piCalculators[i]);
      threads[i].start();
    }

    new Thread(() -> {
      try {
        Thread.sleep(9000);
      } catch (InterruptedException ignored) {

      }
      Signal.raise(new Signal("INT"));
    }).start();

    while (!stop.get()) {
      if (stop.get()){
        for (int i = 0; i < threadCnt; i++) {
          piCalculators[i].setStop();
        }
      }
    }
    double res = 0;
    for (int i = 0; i < threadCnt; i++) {
      res += piCalculators[i].getRes();
    }
    res *= 4;
    System.out.println(res);
    System.exit(0);
  }
}
