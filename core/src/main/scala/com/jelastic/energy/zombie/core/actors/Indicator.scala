package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.{Mesh, Color}
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.Gdx

class Indicator extends Actor {

  private var progress: Float = 0
  val shapeRenderer = new ShapeRenderer()

  override def draw(batch: SpriteBatch, parentAlpha: Float) {
    val xOffset = getParent.getWidth * .2
    val yOffset = getParent.getHeight * .6

    val width = getParent.getWidth * .6
    val height = getParent.getHeight * .2

    shapeRenderer.setColor(Color.RED)
    shapeRenderer.begin(ShapeType.Filled)
    shapeRenderer.rect(xOffset.toFloat, yOffset.toFloat, width.toFloat, height.toFloat)
    shapeRenderer.end()
  }

  override def act(delta: Float) {
    progress += .1f
  }
}
