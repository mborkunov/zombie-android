package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Indicator(callback : (Int) => Unit) extends Image(new Texture(Gdx.files.internal("gfx/zombie/indicator.png"))) {

  private var started = false

  private var progress: Float = 1

  setHeight(3)
  setWidth(50)
  var percentage = 100f

  override def draw(batch: Batch, parentAlpha: Float) {
    if (!started) return
    setOrigin(getWidth / 2, 0)
    setPosition(getParent.getWidth / 2 - getWidth / 2, 13)
    setScaleX(percentage / 100)
    if (!getParent.asInstanceOf[Target].front)
    super.draw(batch, parentAlpha)
  }

  override def act(delta: Float) {
    if (started) {
      percentage = percentage - .5f

      if (percentage <= 0) {
        percentage = 0
        started = false
        callback(1)
      }
    }
    //progress += .1f
  }
  def start() = {
    started = true
    percentage = 100f
  }
  def stop() = started = false
}
