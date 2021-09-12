import java.util.List;

public abstract class AQuantifier implements IQuantifier {
  protected IProposition given;

  public AQuantifier(IProposition given) {
    this.given = given;
  }

}
