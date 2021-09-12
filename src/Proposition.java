import java.util.List;

public abstract class Proposition implements IProposition<IMathObject> {

  protected List<IQuantifier> quantifiers;

  Proposition(List<IQuantifier> quantifiers) {
    this.quantifiers = quantifiers;
  }


  @Override
  public String toString() {
    String rendered = "";
    for (IQuantifier q : quantifiers) {
      rendered += q.toString() + " ";
    }

    return rendered;
  }

  @Override
  public String toLaTeX() {
    String latex = "";

    for(IQuantifier q : quantifiers) {
      latex += q.toLaTeX() + " ";
    }

    return latex;
  }

  @Override
  public IProof proveMe() {
    return null;
  }
}
