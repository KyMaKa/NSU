
public class Main implements Runnable {

  String[] str;

  public Main(String[] str) {
    this.str = str;
  }

  private void threadParent() throws InterruptedException {


  }

  public void run() {
    print(str);
  }
  private void print(String[] strs) {
    for (String s : strs)
      System.out.println(s);
  }

  public static void main(String[] args) throws InterruptedException {

    String[] str1 = new String[] {"Thread11", "Thread12"};
    String[] str2 = new String[] {"Thread21", "Thread22"};
    String[] str3 = new String[] {"Thread31", "Thread32"};
    String[] str4 = new String[] {"Thread41", "Thread42"};

    Main thread1 = new Main(str1);
    Thread t1 = new Thread(thread1);
    t1.start();

    Main thread2 = new Main(str2);
    Thread t2 = new Thread(thread2);
    t2.start();

    Main thread3 = new Main(str3);
    Thread t3 = new Thread(thread3);
    t3.start();

    Main thread4 = new Main(str4);
    Thread t4 = new Thread(thread4);
    t4.start();
  }
}