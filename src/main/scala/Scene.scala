trait Scene:
  val gameObjects: () => Iterable[GameObject[?]]

object Scene:
  private case class SceneImpl(gameObjects: () => Iterable[GameObject[?]])
      extends Scene

  def apply(gameObjects: () => Iterable[GameObject[?]]): Scene =
    SceneImpl(gameObjects)
