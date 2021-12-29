public class main {
  public static void main(String[] args) throws InterruptedException {
    Company company = new Company(4);
    Founder founder = new Founder(company);
    founder.start();
  }
}
