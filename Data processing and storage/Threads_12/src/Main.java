import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      List<String> list = new LinkedList<>();
      boolean stop = false;
      Thread sorter =
          new Thread(
              () -> {
                while (!Thread.currentThread().isInterrupted()) {
                  try {
                    Thread.sleep(5000);
                  } catch (InterruptedException e) {
                    break;
                  }
                  synchronized (list) {
                    list.sort(String::compareTo);
                  }
                }
              });
      sorter.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      while (!stop) {
        String string = "";
        try {
          string = reader.readLine();
        } catch (IOException ignored) {

        }
        if (string.isEmpty()) {
          sorter.interrupt();
          list.forEach(System.out::println);
          stop = true;
        } else {
          if (string.length() > 80) {
            String[] strings = string.split("(?<=\\G.{4})");
            for (final String s : strings) {
              synchronized (list) {
                list.add(s);
              }
            }
          } else {
            synchronized (list) {
              list.add(string);
            }
          }
        }
      }
      try {
        reader.close();
      } catch (IOException ignored) {

      }
    }
  }