import java.util.List;

public class ForAll extends AQuantifier {

  ForAll(IProposition given) {
    super(given);
  }

  @Override
  public String toLaTeX() {
    return "\\forall " + given.toLaTeX();
  }

  public String toSring() {
    return "";
  }


}
