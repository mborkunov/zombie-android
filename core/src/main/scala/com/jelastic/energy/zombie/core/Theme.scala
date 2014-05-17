package com.jelastic.energy.zombie.core


import collection.mutable

object Theme {

  /*private var font: Font = null


  private val themes:List[String] = List("zombie", "cat")
  private val data:Map[String, Map[String, Any]] = Map[String, Map[String, Any]](
    "zombie" -> Map("color" -> Color.YELLOW, "bar" -> new Color(.5f, .7f, .5f, 1f)),
    "cat" -> Map("color" -> Color.WHITE, "bar" -> new Color(.82f, .51f, .51f, 1f))
  )
  private val sounds: mutable.HashMap[String, Sound] = new mutable.HashMap()
  private var startButton: TextureRegion = null
  private var tiles: TiledTextureRegion = null
  private var tilesAtlas: BitmapTextureAtlas = null
  private var startButtonAtlas: BitmapTextureAtlas = null
  private var fontAtlas: BitmapTextureAtlas = null
  private val fontPath: String = "DroidLogo-Bold.ttf";
  var themeName: String = themes(0)

  lazy private val activity: Activity = Activity.self
  def load() {
    for (soundId <- List("hit", "fail", "button")) {
      sounds.put(soundId, Resources.loadSound(themeName + "/" + soundId + ".ogg"))
    }
    getStartButton
    getTiles
    getFont
  }

  def color = data(themeName).get("color").get.asInstanceOf[Color]

  def progressColor: Color = data(themeName).get("bar").get.asInstanceOf[Color]

  def reload() {
    font = null
    getFont
    for ((id, sound) <- sounds) {
      sound match {
        case sound: Sound => sound.release()
      }
    }
    sounds.clear()
    for (soundId <- List("hit", "fail", "button")) {
      sounds.put(soundId, Resources.loadSound(themeName + "/" + soundId + ".ogg"))
    }

    tilesAtlas.clearTextureAtlasSources()
    BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(tilesAtlas, activity, themeName + "/tiles.png", 0, 0, 7, 1)

    startButtonAtlas.clearTextureAtlasSources()
    BitmapTextureAtlasTextureRegionFactory.createFromAsset(startButtonAtlas, activity, themeName + "/start.png", 0, 0)
  }


  def getFont: Font = {
    if (font == null) {
      Resources.loadFont(fontPath, 512, 256, 40) match { // fixme activity.scene.layout.getFontHeight
        case (a, f) => {
          font = f
          fontAtlas = a
        }
      }
    }
    font
  }

  def getTiles: TiledTextureRegion = {
    if (tiles == null) {
      Resources.loadTexture(themeName + "/tiles.png", 1024, 128, 7, 1) match {
        case (a, b) => {
          tiles = a
          tilesAtlas = b
        }
      }
    }
    tiles
  }

  def getStartButton: TextureRegion = {
    if (startButton == null) {
      Resources.loadTexture(themeName + "/start.png", 256, 64) match {
        case (a, b) => {
          startButton = a
          startButtonAtlas = b
        }
      }
    }
    startButton
  }

  def playSound(soundId: String) {
    sounds.get(soundId) match  {
      case None =>
      case Some(sound: Sound) => if (activity.getOptions.isSound) sound.play()
      case sound: Sound => if (activity.getOptions.isSound) sound.play()
    }
  }

  def switch() {
    val index: Int = themes.indexOf(themeName)
    themeName = if (index + 1 >= themes.length) themes(0) else themes(index + 1)
    reload()
  }
*/

}
