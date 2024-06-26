import Dimensions2D.*

object Physics2D:
  /** Gives the colliders the methods to implement in order to detect the collisions with other colliders
    * 
    */
  trait Collider extends Positionable with Scalable:
    def collides(other: RectCollider): Boolean
  
  /** Gives the capability to detect an AABB collision to a Behaviour.
    * Width and Height are scaled based on ScaleX and ScaleY of the Dimensions2D.Scalable trait
    * The shape of this collider is Rectangle
    *
    * @param width
    *   width of the collider, must be greater than zero otherwise throws an IllegalArgumentException
    * @param height
    *   height of the collider, must be greater than zero otherwise throws an IllegalArgumentException
    */
  trait RectCollider(private var width: Double, private var height: Double)
      extends Collider:
    require(width > 0)
    require(height > 0)

    def colliderWidth: Double = width * scaleX
    def colliderHeight: Double = height * scaleY

    def colliderWidth_=(w: Double): Unit = if w > 0 then width = w
    def colliderHeight_=(h: Double): Unit = if h > 0 then height = h

    /** Detect if this Behaviour collided with another Collider
      *  using an AABB collision detection algorithm
      *
      * @param other
      * @return
      *   true if a collision is detected, false otherwise
      */
    def collides(other: RectCollider): Boolean =
        this.y <= other.y + other.height &&
        this.x <= other.x + other.width &&
        this.y + this.height >= other.y &&
        this.x + this.width >= other.x
  
  trait CircleCollider(r: Double) extends Collider:
    def collides(other: RectCollider): Boolean = ???