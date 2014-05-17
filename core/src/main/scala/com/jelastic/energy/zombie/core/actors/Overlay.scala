package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.scenes.scene2d._
import com.badlogic.gdx.graphics.g2d.{Sprite, TextureRegion, SpriteBatch}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.ui.{Image, Skin, TextButton}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{GL10, Texture}
import com.jelastic.energy.zombie.core.{Audio, Layout}

class Overlay(layout: Layout) extends Group {

  class StartButton extends Actor {

    val sprite:Sprite = new Sprite(new Texture(Gdx.files.internal("gfx/zombie/start.png")))

    override def draw(spriteBatch: SpriteBatch, parentAlpha: Float) {
      val scale: Float = getWidth / sprite.getRegionWidth / 3
      sprite.setScale(scale, scale)
      sprite.setPosition(getOriginX - sprite.getWidth / 2, getOriginY - sprite.getHeight / 2)
      sprite.draw(spriteBatch)
    }

    override def hit(x: Float, y: Float, touchable: Boolean): Actor =
      if (sprite.getBoundingRectangle.contains(x, y)) this else null
  }

  val shapeRenderer: ShapeRenderer = new ShapeRenderer()

  setPosition(0, 0)

  val button: StartButton = new StartButton()
  button.addListener(new InputListener {
    override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
      Audio.BUTTON.play()
      setVisible(false)
      true
    }
  })

  private val buttonWidth: Float = 1000
  private val buttonHeight: Float = 100
  println(layout, (layout.width - buttonWidth) / 2, (layout.height - buttonHeight)/ 2)

  button.setOrigin(layout.width / 2, (layout.height - buttonHeight)/ 2)
  button.setPosition(layout.width / 2, layout.height / 2)
  button.setWidth(buttonWidth)
  button.setHeight(buttonHeight)

  addActor(button)

  override def draw(batch: SpriteBatch, parentAlpha: Float) {
    batch.end()
    Gdx.gl.glEnable(GL10.GL_BLEND)
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
    shapeRenderer.setColor(0, 0, 0, .7f)
    shapeRenderer.rect(0, 0, layout.width, layout.height)
    shapeRenderer.end()
    Gdx.gl.glDisable(GL10.GL_BLEND)
    batch.begin()
    drawChildren(batch, parentAlpha)
  }

  override def hit(x: Float, y: Float, touchable: Boolean): Actor = {
    for (child: Actor <- getChildren.begin()) {
        if (child.hit(x, y, touchable) != null) return child
      }
      if (x > 0 && x < layout.width && y > 0 && y < layout.height) this else null
    }
}
