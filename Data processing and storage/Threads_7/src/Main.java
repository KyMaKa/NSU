import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    int steps = 1000000;
    Scanner scanner = new Scanner(System.in);
    int threadCnt = scanner.nextInt();

    Thread[] threads = new Thread[threadCnt];
    CalculatePi[] piCalculators = new CalculatePi[threadCnt];

    for (int i = 0; i < threadCnt; i++) {
      piCalculators[i] = new CalculatePi(i * steps / threadCnt, (i + 1) * steps / threadCnt, steps);
      threads[i] = new Thread(piCalculators[i]);
      threads[i].start();
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
