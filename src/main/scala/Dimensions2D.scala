object Dimensions2D :
    /** Add 2D position to a Behaviour
    *
    * @param x
    *   position of the Behaviour on the X axis
    * @param y
    *   position of the Behaviour on the Y axis
    */
  trait Positionable(var x: Double = 0, var y: Double = 0) extends Behaviour

  /** Gives to other behaviours a scale on X and Y of their dimension.
    *
    * @param x
    *   multiplier of the width, must be greater than 0.
    * @param y
    *   multiplier of the height, must be greater than 0.
    */
  trait Scalable(private var x: Double, private var y: Double)
      extends Behaviour:

    def scaleX: Double = if x > 0 then x else 1
    def scaleY: Double = if y > 0 then y else 1

    def scaleX_=(w: Double): Unit =
      if w > 0 then this.x = w

    def scaleY_=(h: Double): Unit =
      if h > 0 then this.y = h