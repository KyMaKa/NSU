public class CalculatePi implements Runnable {
  //private int steps;
  private int from;
  private int to;
  private int step;
  private boolean stop = false;

  public CalculatePi(int from, int to, int step) {
    this.from = from;
    this.to = to;
    this.step = step;
  }

  public void setStop() {
    stop = true;
  }

  public boolean getStop() {
    return stop;
  }

  private double calculatePi(int steps) {
    return 1d/(double) (2 * steps + 1) * (steps % 2 == 0 ? 1 : -1);
  }

  private double res = 0;

  @Override
  public void run() {
    for (int i = from; i < to; i++)
      res += calculatePi(i);

    from += step;
    to += step;
  }

  public double getRes() {
    return res;
  }
}
