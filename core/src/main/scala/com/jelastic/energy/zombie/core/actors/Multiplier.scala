package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor

class Multiplier extends Actor {

  val renderer: ShapeRenderer = new ShapeRenderer()

  override def draw(batch: Batch, parentAlpha: Float) {
    val parentWidth = getParent.getWidth
    val parentHeight = getParent.getHeight

    renderer.begin(ShapeRenderer.ShapeType.Filled)
    renderer.setColor(1f, 0, 0, .5f)
    renderer.rect(0, 0, parentWidth / 2, parentHeight / 2)
    renderer.end()
  }

  override def hit(x: Float, y: Float, touchable: Boolean): Actor = null
}
