package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{Batch, Sprite, TextureRegion}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.utils.Align
import com.badlogic.gdx.scenes.scene2d.{Actor, Group, InputEvent, InputListener}
import com.jelastic.energy.zombie.core.Audio

class Target(layout: Layout, col: Int, row: Int) extends Group {

  def getCol = col


  def getRow = row
  val indicator: Indicator = new Indicator

  val textures: Array[TextureRegion] = TextureRegion.split(new Texture(Gdx.files.internal("gfx/zombie/tiles.png")), 128, 128)(0)

  var angleStep: Int = 7

  var angle: Int = 90
  var front: Boolean = true
  private val _sprite: Sprite = new Sprite(textures(0))

  var scale: Float = layout.size.toFloat / _sprite.getRegionHeight

  val shapeRenderer: ShapeRenderer = new ShapeRenderer()
  var textureIndex = 0

  var scaleAngle = 0


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
  }

  def miss(): Unit = {
    Audio.FAIL.play()
  }

  override def draw(batch: Batch, alpha: Float) {
    setScale(math.sin(angle * math.Pi / 180f).toFloat * scale, scale)
    scale = layout.size.toFloat / _sprite.getRegionHeight
    _sprite.setPosition(getX, getY)
    _sprite.setRegion(textures(textureIndex))
    _sprite.setScale(getScaleX, getScaleY)
    _sprite.draw(batch)
    super.draw(batch, alpha)
  }

  override def act(delta: Float) {
    if (_rotating) {
      scaleAngle = scaleAngle + 1
      angle += (math.random * angleStep).round.toInt
    }
    if (angle > 360) angle = 0

    if (angle < 180 && angle + angleStep >= 180) {
      front = false
      if (!front) {
        textureIndex = 1 + (math.random * 3).floor.toInt
      }
      _sprite.flip(math.random > .5, false)
    } else if (angle < 360 && angle + angleStep >= 360) {
      front = true
      textureIndex = 0
    }
    super.act(delta)
  }

  def rotate(flips: Byte) {
    _flips = flips
    _rotating = true
  }

  override def hit(x: Float, y: Float, touchable: Boolean): Actor = {
    if (_sprite.getBoundingRectangle.contains(localToParentCoordinates(new Vector2(x, y)))) this else null
  }

  var _rotating: Boolean = false
  var _flips: Byte = 0

  def rotating = _rotating

  override def toString: String = s"Target: $col x $row, $getX x $getY"
}
