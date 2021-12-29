public class Main implements Runnable {

  public void run() {
    while (!Thread.interrupted())
      System.out.println("Some text string from Child");
    System.out.println("Thread interrupted");
  }

  public static void main(String[] args) throws InterruptedException {
    Main a = new Main();
    Thread ct = new Thread(a);
    ct.start();
    Thread.sleep(2000);
    ct.interrupt();
  }
}
