import java.util.function.BiFunction;

public class Main {

  public static void main(String[] args) {
    Set<Integer> oneThruSeven = Set.buildSet((x -> x), 7);
    BiFunction<Integer, Integer, Integer> additionModSeven = ((x, y) -> (x + y) % 7);
    Group<Integer> Z_7 = new Group<>(oneThruSeven, additionModSeven, 0);

    System.out.println("My program claims that \"Z_7 is a group\" is " + Z_7.validate());
  }

}
