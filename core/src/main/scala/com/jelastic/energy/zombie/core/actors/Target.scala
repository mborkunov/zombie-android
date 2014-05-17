package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.scenes.scene2d.{Group, InputEvent, InputListener, Actor}
import com.badlogic.gdx.graphics.g2d.{TextureRegion, Sprite, SpriteBatch}
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.jelastic.energy.zombie.core.{Audio, Layout}

class Target(layout: Layout, col: Int, row: Int) extends Group {

  val indicator: Indicator = new Indicator

  val textures: Array[TextureRegion] = TextureRegion.split(new Texture(Gdx.files.internal("gfx/zombie/tiles.png")), 128, 128)(0)

  var angleStep: Int = 5
  var angle: Int = 0
  var front: Boolean = true

  val _sprite: Sprite = new Sprite(textures(0))

  val scale: Float = layout.size.toFloat / _sprite.getRegionHeight
  val shapeRenderer: ShapeRenderer = new ShapeRenderer()

  layout.setTargetLocation(this, col, row)
  var textureIndex = 0

  var scaleAngle = 0

  addListener(new InputListener {
    override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
      if (front || textureIndex >= 4) {
        Audio.FAIL.play()
      } else {
        textureIndex = textureIndex + 3
        Audio.HIT.play()
      }
      true
    }
  })

  addActor(indicator)
  def sprite() = _sprite

  setWidth(_sprite.getBoundingRectangle.getWidth)
  setHeight(_sprite.getBoundingRectangle.getHeight)

  override def draw(batch: SpriteBatch, parentAlpha: Float) {
    _sprite.setRegion(textures(textureIndex))
    _sprite.setScale(math.sin(angle * math.Pi / 180f).toFloat * scale, scale)
    _sprite.draw(batch)
    super.draw(batch, parentAlpha)
  }

  override def act(delta: Float) {
    scaleAngle = scaleAngle + 1
    angle += (math.random * angleStep).round.toInt
    if (angle > 360) angle = 0

    if (this.angle < 180 && this.angle + angleStep >= 180) {
      front = false
      if (!front) {
        textureIndex = 1 + (math.random * 3).floor.toInt
      }
      _sprite.flip(math.random > .5, false)
    } else if (this.angle < 360 && this.angle + angleStep >= 360) {
      front = true
      textureIndex = 0
    }
  }

  override def hit(x: Float, y: Float, touchable: Boolean): Actor = {
    if (_sprite.getBoundingRectangle.contains(x, y)) this else null
  }

  override def toString: String = s"Target: $col x $row"
}
