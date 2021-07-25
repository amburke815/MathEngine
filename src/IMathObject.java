public interface IMathObject {

  EMathObject getType();

  boolean validate();

  IMathObject copy();

  @Override
  boolean equals(Object o);

  @Override
  int hashCode();

  @Override
  String toString();


}
