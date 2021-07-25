import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * An algebraic group over some set of <code>X</code>
 * <br><i><b>INVARIANT:</b></i> the identity element is always inside the group. Enforced through
 * rely-guarantee
 */
// TODO: maybe put group predicates in the set class
public class Group<X> extends AMathObject {

  private final Set<X> set;
  private final BiFunction<X, X, X> binaryOperation;
  private final X identity;


  /**
   * Initializes a group consisting of only the identity element
   */
  public Group(BiFunction<X, X, X> binaryOperation, X identity) {
    super(EMathObject.GROUP);
    this.set = new Set<>(identity);
    this.binaryOperation = binaryOperation;
    this.identity = identity; // for quick access
  }

  /**
   * Initializes a group consisting of a prespecified set
   */
  public Group(Set<X> set, BiFunction<X, X, X> binaryOperation, X identity) {
    super(EMathObject.GROUP);
    this.set = set;
    this.set.add(identity); // rely-guarantee
    this.binaryOperation = binaryOperation;
    this.identity = identity;
  }

  /**
   * Initialize a group by specifying its elements
   */
  public Group(BiFunction<X, X, X> binaryOperation, X identity, X... set) {
    super(EMathObject.GROUP);
    this.set = new Set<>(set);
    this.binaryOperation = binaryOperation;
    this.identity = identity;
  }

  /**
   * Gets the elements in this Group as a {@link Set} <i>as a copy</i>
   */
  public Set<X> getElements() {
    return (Set) this.set.copy();
  }

  /**
   * Gets the elements in this Group as a {@link Set} <i>as a (immutable) reference</i>
   */
  public Set<X> elements() {
    return this.set;
  }

  /**
   * Gets this Group's binary operation <i>as an immutable reference</i>
   */
  public BiFunction<X, X, X> getBinaryOperation() {
    return this.binaryOperation;
  }

  /**
   * Does this group satisfy the closure property?
   * <br><i><b>∀g,g'∈G (gg'∈G)∧(g'g∈G)</b></i>
   */
  public final boolean hasClosure() { // O(n^2)
    // find a way to do this with better time complexity. This is brute force, can probably rewrite
    // w/ a smart map and andMap usage.
    boolean hasClosure = true;
    for (X x1 : set.getItems()) {
      for (X x2 : set.getItems()) {
        hasClosure &= this.set.contains(binaryOperation.apply(x1, x2));
      }
    }
    return hasClosure;
  }

  /**
   * Does this Group satisfy the associative property?
   * <br><i><b>∀a,b,c∈G (ab)c=a(bc)</b></i>
   */
  public boolean hasAssociativity() { // O(n^3)
    // find a way to do this with better time complexity. This is brute force, can probably rewrite
    // w/ a smart map and andMap usage.
    boolean hasAssociativity = true;
    for (X x1 : set.getItems()) {
      for (X x2 : set.getItems()) {
        for (X x3 : set.getItems()) { // ugly
          hasAssociativity &=
              binaryOperation.apply(binaryOperation.apply(x1, x2), x3).equals(
                  binaryOperation.apply(x1, binaryOperation.apply(x2, x3)));
        }
      }
    }
    return hasAssociativity;
  }

  /**
   * Does this group have an identity element?
   * <br><i><b>∃e∈G: ∀g∈G eg=ge=g</b></i>
   */
  public boolean hasIdentity() {
    return set.contains(identity)
        && set.andMap(x -> binaryOperation.apply(identity, x).equals(x))
        && set.andMap(x -> binaryOperation.apply(x, identity).equals(x));
  }

  /**
   * Does this group have inverses?
   * <br><i><b>∀g∈G ∃g⁻¹∈G: gg⁻¹=g⁻¹g=e, e∈G</b></i>
   */
  public boolean hasInverses() { // O(n^2)
    // find a way to do this with better time complexity. This is brute force, can probably rewrite
    // w/ a smart map and orMap usage.
    boolean hasInverses = false;
    for (X x1 : set.getItems()) {
      for (X x2 : set.getItems()) {
        hasInverses |= binaryOperation.apply(x1, x2).equals(
            binaryOperation.apply(x2, x1).equals(identity)); //TODO FIXME
      }
    }
    return hasInverses;
  }

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @Override
  public boolean validate() {
//    return hasClosure()
//        && hasAssociativity()
//        && hasIdentity()
//        && hasInverses();
    boolean closure = hasClosure();
    boolean assoc = hasAssociativity();
    boolean ident = hasIdentity();
    boolean inv = hasInverses();
    return false;
  }

  @Override
  public IMathObject copy() {
    return null;
  }

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Group)) {
      return false;
    }

    Group aGroup = (Group) o;
    return aGroup.getElements().equals(aGroup.getElements())
        && aGroup.getBinaryOperation().equals(aGroup.getBinaryOperation());
  }
}
