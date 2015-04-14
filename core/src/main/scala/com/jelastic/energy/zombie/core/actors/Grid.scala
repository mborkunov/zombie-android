package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.graphics.FPSLogger
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.Actor

class Grid(layout: Layout) extends Actor {

  val fpsLogger = new FPSLogger

  val renderer: ShapeRenderer = new ShapeRenderer
  setSize(layout.width, layout.height)

  override def draw(batch: Batch, parentAlpha: Float) {
    renderer.begin(ShapeRenderer.ShapeType.Line)
    renderer.setColor(1f, 1f, 0f, .5f)

    val step = (getWidth / 10, getHeight / 10)
    for (i <- 1 to 10) {
      renderer.line(0, i * step._2, getWidth, i * step._2)
      renderer.line(i * step._1, 0, i * step._1, getHeight)
    }
    renderer.end()
    fpsLogger.log()
  }
}
