public class Main {


  public static void main(String[] args) {
    Thread child = new Thread(() -> {
      System.out.println("Child thread running");
      for(int i=0; i < 10; i++)
        System.out.println("Some text string");
    });

    for(int i=0; i < 10; i++)
      System.out.println("Some text string");

    child.start();
  }
}
