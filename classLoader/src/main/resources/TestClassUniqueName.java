public class TestClassUniqueName {
  public void methodOne(String text, int c) {
    for(int i = 0; i < c; i++) {
      System.out.println(text + " Hi"+i);
    }
  }

  public static void methodTwo(String text) {
    System.out.println(text + " from static method");
  }
}
