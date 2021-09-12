public class Variable<X> implements IMathObject {

  private X value;
  private String identifier;

  Variable(X value, String name) {
    this.value = value;
    this.identifier = name;
  }

  public String toString() {
    return identifier;
  }

  @Override
  public EMathObject getType() {
    return EMathObject.VARIABLE;
  }

  @Override
  public boolean validate() {
    return true; // FIXME
  }

  @Override
  public IMathObject copy() {
    return new Variable(value, identifier);
  }
}
