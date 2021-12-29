import java.util.ArrayList;
import java.util.List;

public final class Founder {
  private final List<Runnable> workers;
  private final Company company;
  public Founder(final Company company) {
    this.company = company;
    this.workers = new ArrayList<>(company.getDepartmentsCount());
  }
  private void fill() {
    int i = 0;
    while(i < company.getDepartmentsCount()){
      workers.add(new Worker(i, company));
      i++;
    }
  }
  public void start() throws InterruptedException {
    fill();
    for (final Runnable worker : workers) {
      Thread thread = new Thread(worker);
      thread.start();
      thread.join();
    }
    company.showCollaborativeResult();
  }

}