public interface IProposition<I> extends IProvable<IMathObject> {

  String toLaTeX();


  @Override
  String toString();


  @Override
  int hashCode();

}
