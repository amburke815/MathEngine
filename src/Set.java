import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A Set of some {@link IMathObject}.
 * <br>Unordered and contains no duplicates.
 */
public class Set<X/*extends IMathObject*/> extends AMathObject {

  private List<X> items;

  /**
   * Makes an empty set
   */
  public Set() {
    super(EMathObject.SET);
    items = new ArrayList<>();
  }


  /**
   * Makes a set with items
   */
  public Set(X... items) {
    this(); // start with an empty set
    for (X item : items) {
      this.add(item);
    }
  }

  /**
   * Adds an item to the set, and ensures duplicates are not added
   */
  public void add(X item) {
    if (!this.items.contains(item)) {
      this.items.add(item);
    }
  }

  /**
   * Removes an item from this set by matching for equality
   */
  public void remove(X item)
      throws IllegalArgumentException {
    if (this.items.contains(item)) {
      this.items.remove(item);
    } else {
      throw new IllegalArgumentException("Could not remove item \"" + item.toString() + "\"");
    }
  }

  /**
   * Removes an item by its index
   */
  public void remove(int indexToRemove) {
    this.items.remove(validateIndex(indexToRemove, 0, this.items.size()) -  1);
  }

  /**
   * Returns a {@link List} of {@link IMathObject}s containing the items in this {@link Set}
   * <br><i>NOTE:</i> this returns a reference to the list contained in this <code>Set</code>
   */
  public List<X> getItems() {
    return this.items;
  }

  /**
   * Returns the item at the specified index, throws if invalid index is passed.
   */
  public X get(int index) {
    return this.getItems().get
        (validateIndex(index, 0, this.getItems().size() - 1));
  }

  /**
   * Returns the size of this Set
   */
  public int size() {
    return this.items.size();
  }

  /**
   * Does this Set contain the given element?
   */
  public boolean contains(X toCheck) {
    return this.items.contains(toCheck);
  }

  /**
   * Maps a function onto all of the elements of a {@link Set} and returns it
   */
  public <Y> Set<Y> map(Function<X, Y> toMap) {
    Set<Y> mapped = new Set<>();
    for (X x : this.getItems()) {
      mapped.add(toMap.apply(x));
    }
    return mapped;
  }

  /**
   * Filter a set based on some predicate and return it
   */
  public Set<X> filter(Predicate<X> toTest) {
    Set<X> filtered = new Set<>();
    for (X x : this.getItems()) {
      if (toTest.test(x)) {
        filtered.add(x);
      }
    }
    return filtered;
  }

  /**
   * Combines two elements at a time from left to right, starting with the base case.
   */
  public <Y, Z> Y foldr(BiFunction<X, Y, Y> foldWith, Y base) {
    Y folded = base;
    for (int i = 0; i < this.size(); i++) {
      folded = foldWith.apply(this.get(i), folded);
    }
    return folded;
  }

  /**
   * Check a condition for at least one element in the Set
   */
  public boolean orMap(Predicate<X> toCheck) {
    boolean satisfiesOnce = false;
    for (X x : this.getItems()) {
      satisfiesOnce |= toCheck.test(x);
    }
    return satisfiesOnce;
  }

  /**
   * Checks if a condition is true for all elements in the Set
   */
  public boolean andMap(Predicate<X> toCheck) {
    boolean satisfiesAll = true;
    for (X x : this.getItems()) {
      satisfiesAll &= toCheck.test(x);
    }
    return satisfiesAll;
  }

  /**
   * Builds a set where each element is made by transforming the index at which it will live
   * <br><i>NOTE:</i> set is not guaranteed to be as large as specified in the case that the
   * function is not well-defined.
   */
  public static <Y> Set<Y> buildSet(Function<Integer, Y> toApply, int nElements) {
    Set<Y> built = new Set<>();
    for (int i = 0; i < validateIndex(nElements, 0, Integer.MAX_VALUE); i++) {
      built.add(toApply.apply(i));
    }
    return built;
  }

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  @Override
  public boolean validate() { // this should never return false. TODO see if this can be simplified
    // in design
    boolean hasDuplicates = false;
    return andMap(x -> this.items.subList(this.getItems().indexOf(x), this.size()).contains(x));
  }

  @Override
  public IMathObject copy() {
    return map(x -> x);
  }

  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Set)) {
      return false;
    }

    Set aSet = (Set) o;
    if (this.size() != aSet.size()) {
      return false;
    }
    return map(x -> x.equals(aSet.get(this.items.indexOf(x)))).andMap(x -> x == true);
  }

  @Override
  public int hashCode() {
    return Objects.hash(items, type);
  }

  @Override
  public String toString() {
    String thisSet = "{";
    for (X x : this.items) {
      thisSet += x.toString() + ", ";
    }

    return thisSet += "}";
  }


  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  private static int validateIndex(int toValidate, int lowerBound, int UpperBound) {
    if (toValidate < 0 || toValidate > UpperBound) {
      throw new IllegalArgumentException("Index to remove from \"" + toValidate + "\" out of "
          + "bounds for list indices [" + lowerBound + "," + UpperBound + "]");
    }
    return toValidate;
  }

}
