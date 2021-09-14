package draftSep13;

/**
 * IDEA:
 *
 * Use S Expression nested list/tree data structure to store math and english expressions
 *
 *
 *                        ;; Proposition ;;
 * {[(forall) -> (x in Z)] -> [(forall) -> (y in Z)]} -> {[(2|x) -> (AND) -> (2|y)] -> (THEN) -> [(2|x+y)]}
 *
 * ;; An ILanguageObect is one of:
 * ;; - Quantifier
 * ;; - PropositionElement
 *
 * ;; A Quantifier is one of:
 * ;; - forAll(PropositionElement)
 * ;; - thereExists(PropositionElement)
 *
 *
 * ;; a PropositionElement/IMathLanguageObject is one of:
 * ;; - Quantifier
 * ;; - [PropositionElement] // NESTED LIST
 *
 * ;; A [PropositionElement] is one of:
 * ;; - Period
 * ;; - (PropositionElement, [PropositionElement])
 *
 * ;; A Connective is one of:
 * ;; (ILanguageObject
 *                           ;; Proof ;;
 * SPS([(2|x) -> (AND) -> (2|y)]) -> THEN([x = 2m, m in Z] -> (AND) -> [y = 2n, n in Z]) -> NOW([2|x+y == 2|2n+2m == 1|n+m == T])
 */


public interface ILanguageObject {

  ILanguageObject consToLanguageObject(ILanguageObject consTo);

  String toLaTeX();

  @Override
  String toString();

  @Override
  boolean equals(Object o);

  @Override
  int hashCode();

}
