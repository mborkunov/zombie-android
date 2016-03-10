package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{Batch, Sprite, TextureRegion}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.{Actor, Group, InputEvent, InputListener}
import com.badlogic.gdx.utils.Align
import com.jelastic.energy.zombie.core.{Constants, Audio}
import Constants._

class Target(layout: Layout, col: Int, row: Int) extends Group {

  def getCol = col


  def getRow = row
  val indicator: Indicator = new Indicator(this.rotate)

  val textures: Array[TextureRegion] = TextureRegion.split(new Texture(Gdx.files.internal("gfx/zombie/tiles.png")), 128, 128)(0)

  var angleSpeed: Double = HALF_PI * 8

  var angle: Double = 0
  var front: Boolean = true
  private val _sprite: Sprite = new Sprite(textures(0))

  var scale: Float = layout.size.toFloat / _sprite.getRegionHeight

  val shapeRenderer: ShapeRenderer = new ShapeRenderer()
  var textureIndex = 0


  addListener(new InputListener {
    override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
      if (front || textureIndex >= 4) {
        miss()
      } else {
        kill()
      }
      true
    }
  })

  addActor(indicator)

  setSize(_sprite.getBoundingRectangle.getWidth, _sprite.getBoundingRectangle.getHeight)
  setOrigin(Align.center)
  def sprite = _sprite

  def kill(): Unit = {
    textureIndex = textureIndex + 3
    Audio.HIT.play()
    rotate(1)
    indicator.stop()
  }

  def miss(): Unit = {
    Audio.FAIL.play()
  }

  override def draw(batch: Batch, alpha: Float) {
    setScale(math.cos(angle).toFloat * scale, scale)
    _sprite.setPosition(getX, getY)
    _sprite.setRegion(textures(textureIndex))
    _sprite.setScale(getScaleX, getScaleY)
    _sprite.draw(batch)
    super.draw(batch, alpha)
  }

  private var _rotating: Boolean = false
  private var _flips: Int = 0

  override def act(delta: Float) {
    if (_rotating) {
      val angleDelta = angleSpeed * delta
      angle += angleDelta

      if (angle >= DOUBLE_PI) {
        angle = 0
      }

      val nextValue: Double = angle + angleDelta

      if (angle <= HALF_PI && nextValue >= HALF_PI) {
        angle = HALF_PI
        front = false
        textureIndex = 1 + (math.random * 3).floor.toInt
        _sprite.flip(math.random > .5, false)
      } else if (angle <= THREE_HALF_PI && nextValue >= THREE_HALF_PI) {
        angle = THREE_HALF_PI
        front = true
        textureIndex = 0
      }

      if (angle % PI <= 0.01) {
        _flips -= 1
        if (_flips <= 0) {
          _rotating = false
          if (!front) {
            indicator.start()
          }
        }
      }
    }
    super.act(delta)
  }

  def rotate(flips: Int) {
    _flips = flips
    _rotating = true
  }

  override def hit(x: Float, y: Float, touchable: Boolean): Actor = {
    if (_sprite.getBoundingRectangle.contains(localToParentCoordinates(new Vector2(x, y)))) this else null
  }

  def rotating = _rotating

  override def toString: String = s"Target: $col x $row, $getX x $getY"
}
