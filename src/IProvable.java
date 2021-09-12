/**
 * X is provable <--> X has finite truth value when <code>evaluate</code> is called.
 */
public interface IProvable<X> {

  /**
   * Returns a <code>IProof</code> that describes the reasoning behind the given provable's truth
   * value
   * @return
   */
  IProof proveMe();

}
