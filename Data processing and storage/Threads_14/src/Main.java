import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  public static void main(String[] args) {
    Semaphore sA = new Semaphore(0);
    Semaphore sB = new Semaphore(0);
    Semaphore sC = new Semaphore(0);

    AtomicInteger cA = new AtomicInteger();
    AtomicInteger cB = new AtomicInteger();
    AtomicInteger cC = new AtomicInteger();
    AtomicInteger cW = new AtomicInteger();

    Thread a = new Thread(()->{
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          break;
        }
        //System.out.println("part A is ready.");
        cA.getAndIncrement();
        System.out.println(cA + " parts A is ready.");
        sA.release();
      }
    });

    Thread b = new Thread(()->{
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          break;
        }
        //System.out.println("part B is ready.");
        cB.getAndIncrement();
        System.out.println(cB + " parts B is ready.");
        sB.release();
      }
    });

    Thread c = new Thread(()->{
      while (!Thread.currentThread().isInterrupted()) {
        try {
          sA.acquire();
          cA.decrementAndGet();
          sB.acquire();
          cB.decrementAndGet();
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          break;
        }
        //System.out.println("part C is ready.");
        cC.getAndIncrement();
        System.out.println(cC + " parts C is ready.");
        sC.release();
      }
    });

    Thread widget = new Thread(()->{
      while (!Thread.currentThread().isInterrupted()) {
        try {
          sC.acquire();
          cC.decrementAndGet();
        } catch (InterruptedException e) {
          break;
        }
        cW.incrementAndGet();
        System.out.println(cW + " widgets is ready");
      }
    });

    a.start();
    b.start();
    c.start();
    widget.start();
  }
}
