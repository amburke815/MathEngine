import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class SumOfTwoEvenIntegersIsEven extends Proposition {
  private final Variable<Integer> a;
  private final Variable<Integer> b;


  SumOfTwoEvenIntegersIsEven() {
    super(new ArrayList<>(
        Arrays.asList(
            new ForAll(new XInZ("a")),
            new ForAll(new XInZ("b"))
        )
    ));
  }
  SumOfTwoEvenIntegersIsEven(int a, int b) {
    this.a = checkEven(a);
    this.b = checkEven(b);
  }

  SumOfTwoEvenIntegersIsEven() {
    this(0,0); // trivial constructor
  }

  @Override
  public IProof proveMe() {

  }

  @Override
  public String toString() {
    return
  }


  private final int checkEven(int toCheck) {
    if (toCheck % 2 == 1) {
      throw new IllegalArgumentException(toCheck + " is not an even number");
    }
    return toCheck;
  }
}
