import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers.*
import Behaviours.*
import org.scalatest.BeforeAndAfterEach

class ColliderTests extends AnyFlatSpec with BeforeAndAfterEach:
  

  //After working engine this can be refactored and moved inside ColliderTest.scala
  object Collider:
    def apply(width: Double, height: Double, x: Double = 0, y: Double = 0)(
        colliderWidth: Double = -1,
        colliderHeight: Double = -1
    ): Collider = new Behaviour
      with Collider(colliderWidth, colliderHeight)
      with Dimensionable(width, height)
      with Positionable(x, y)
  import Collider.*

  val collider = Collider(width = 5, height = 3)()
  val collider2 = Collider(width = 1, height = 2, x = 2, y = -5)()

  override protected def beforeEach(): Unit = 
    collider.colliderHeight = 3
    collider.colliderWidth = 5
    collider.x = 0
    collider.y = 0
    collider2.x = 2
    collider2.y = -5

  "collider" should "initially have its width and height set to dimensionable width and height" in:
    collider.colliderWidth shouldBe 5
    collider.colliderHeight shouldBe 3

  it should "if initially its width and height are less or equal to zero set them to dimensionable width and height" in:
    val collider = Collider(width = 1, height = 8)(colliderWidth = -5, colliderHeight = 0)
    collider.colliderWidth shouldBe 1
    collider.colliderHeight shouldBe 8

  it should "be able to create a collider in a different position" in:
    val collider2 = Collider(width = 5, height = 3, x = 10, y = -7)()
    collider2.x shouldBe 10
    collider2.y shouldBe -7
    
    collider.x shouldBe 0
    collider.y shouldBe 0

  it should "be able to create a collider with different width and height of dimensionable" in:
    val collider = Collider(width = 5, height = 3)(colliderWidth = 4, colliderHeight = 2)
    collider.colliderWidth shouldBe 4
    collider.colliderHeight shouldBe 2

  it should "be able to change its width and height but not accept negative values" in:
    collider.colliderWidth = 10
    collider.colliderHeight = 20

    collider.colliderWidth shouldBe 10
    collider.colliderHeight shouldBe 20

    collider.colliderWidth = 30
    collider.colliderHeight = -7

    collider.colliderWidth shouldBe 30
    collider.colliderHeight shouldBe 20

    collider.colliderWidth = 0
    collider.colliderWidth shouldBe 30
    collider.colliderHeight = 0
    collider.colliderHeight shouldBe 20

  it should "collide with another collider on top" in:
    collider.collides(collider2) shouldBe false
    collider2.collides(collider) shouldBe false

    collider2.x = 2
    collider2.y = -2

    collider.collides(collider2) shouldBe true
    collider2.collides(collider) shouldBe true

    collider2.y = -3

    collider.collides(collider2) shouldBe false
    collider2.collides(collider) shouldBe false

  it should "collide with another collider on left" in:
    collider.collides(collider2) shouldBe false
    collider2.collides(collider) shouldBe false

    collider2.y = 0
    collider2.x = -1

    collider.collides(collider2) shouldBe true
    collider2.collides(collider) shouldBe true

    collider2.x = -2

    collider.collides(collider2) shouldBe false
    collider2.collides(collider) shouldBe false

  it should "collide with another collider on bottom" in:
    collider.collides(collider2) shouldBe false
    collider2.collides(collider) shouldBe false

    collider2.y = 3
    collider2.x = 2

    collider.collides(collider2) shouldBe true
    collider2.collides(collider) shouldBe true

    collider2.y = 4

    collider.collides(collider2) shouldBe false
    collider2.collides(collider) shouldBe false

  it should "collide with another collider on right" in:
    collider.collides(collider2) shouldBe false
    collider2.collides(collider) shouldBe false

    collider2.y = 0
    collider2.x = 5

    collider.collides(collider2) shouldBe true
    collider2.collides(collider) shouldBe true

    collider2.x = 6

    collider.collides(collider2) shouldBe false
    collider2.collides(collider) shouldBe false
    