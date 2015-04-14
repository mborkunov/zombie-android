package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.{Batch, Sprite}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d._
import com.badlogic.gdx.scenes.scene2d.utils.Align
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.Timer.Task
import com.jelastic.energy.zombie.core.{Application, Audio}

class Overlay(layout: Layout, game: Game) extends Group {

  class StartButton extends Actor {

    private val buttonWidth: Float = 350
    private val buttonHeight: Float = 100

    setPosition(layout.width / 2 - buttonWidth / 2, layout.height / 2 - buttonHeight / 2)
    setSize(buttonWidth, buttonHeight)

    val sprite: Sprite = new Sprite(new Texture(Gdx.files.internal("gfx/zombie/start.png")))
    sprite.setOrigin(0, 0)

    override def draw(batch: Batch, parentAlpha: Float) {
      sprite.setPosition(getX, getY)
      sprite.setScale(getWidth / sprite.getRegionWidth, getHeight / sprite.getRegionHeight)
      sprite.draw(batch)
    }

    override def hit(x: Float, y: Float, touchable: Boolean): Actor =
      if (sprite.getBoundingRectangle.contains(x, y)) this else null
  }

  val shapeRenderer: ShapeRenderer = new ShapeRenderer()

  setSize(layout.width, layout.height)
  setPosition(0, 0)

  val button: StartButton = new StartButton()
  button.addListener(new InputListener {
    override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
      game.start()
      true
    }
  })

  addActor(button)

  override def draw(batch: Batch, parentAlpha: Float) {
    batch.end()
    Gdx.gl.glEnable(GL20.GL_BLEND)
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
    shapeRenderer.setColor(0, 0, 0, .7f)
    shapeRenderer.rect(0, 0, getWidth, getHeight)
    shapeRenderer.end()
    Gdx.gl.glDisable(GL20.GL_BLEND)
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
