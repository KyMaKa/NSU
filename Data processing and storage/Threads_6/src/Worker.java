public class Worker implements Runnable {
  int index;
  Company company;
  public Worker(int index, Company company) {
    this.company = company;
    this.index = index;
  }
  @Override
  public void run() {
    company.getFreeDepartment(index).performCalculations();
    //notify();
  }
}
