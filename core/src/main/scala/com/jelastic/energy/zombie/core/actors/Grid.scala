package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.FPSLogger
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Grid extends Actor {

  val fpsLogger = new FPSLogger

  val shapeRenderer: ShapeRenderer = new ShapeRenderer


  override def draw(batch: SpriteBatch, parentAlpha: Float) {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
    shapeRenderer.setColor(1f, 1f, 0f, .5f)

    val width: Int = Gdx.graphics.getWidth
    val height: Int = Gdx.graphics.getHeight

    val step = (width / 10, height / 10)
    for (i <- Range.apply(1, 10)) {
      shapeRenderer.line(0, i * step._2, width, i * step._2)
      shapeRenderer.line(i * step._1, 0, i * step._1, height)
    }
    shapeRenderer.end()
    fpsLogger.log()
  }
}
