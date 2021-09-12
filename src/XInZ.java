public class XInZ extends Proposition {
  private final Variable<Integer> x;

  XInZ(String variableName) {
    this(0, variableName);
  }

  XInZ(Variable<Integer> x) {
    this.x = x;
  }

  XInZ(Integer value, String variableName) {
    this.x = new Variable(value, variableName);
  }

  @Override
  public String toLaTeX() {
    return "$ " + x.toString() + " \\in \\mathbb{Z}$";
  }

}
