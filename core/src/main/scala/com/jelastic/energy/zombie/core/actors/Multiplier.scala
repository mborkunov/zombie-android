package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class Multiplier extends Actor {

  val shapeRenderer: ShapeRenderer = new ShapeRenderer()

  override def draw(batch: SpriteBatch, parentAlpha: Float) {
    val parentWidth = getParent.getWidth
    val parentHeight = getParent.getHeight

    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
    shapeRenderer.setColor(1f, 0, 0, .5f)
    shapeRenderer.rect(0, 0, parentWidth / 2, parentHeight / 2)
    shapeRenderer.end()
  }

  override def hit(x: Float, y: Float, touchable: Boolean): Actor = null
}
