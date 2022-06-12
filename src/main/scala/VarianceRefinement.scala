object VarianceRefinement {
  trait Contra[F] {
    def method() :Unit = {
//      type Refine = F { type Self <: Refine }
    }
  }
}
