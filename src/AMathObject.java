/**
 * Abstract base class for {@link IMathObject}s. Handles types.
 */
public abstract class AMathObject implements IMathObject{
  protected final EMathObject type;

  /**
   * Abstract initializer for type.
   */
  public AMathObject(EMathObject type) {
    this.type = type;
  }

  @Override
  public EMathObject getType() {
    return this.type;
  }
}
