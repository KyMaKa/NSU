import java.util.concurrent.atomic.AtomicBoolean;

public class Main {


  public static void main(String[] args) {
    AtomicBoolean flag = new AtomicBoolean(false);
    Thread child = new Thread(() -> {
      System.out.println("Child thread running");
      for(int i=0; i < 10; i++)
        System.out.println("Some text string from child");
      flag.set(true);
    });
    child.start();

    while (!flag.get());
    for(int i=0; i < 10; i++)
      System.out.println("Some text string from parent");

  }
}

