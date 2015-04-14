package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Indicator extends Image(new Texture(Gdx.files.internal("gfx/zombie/indicator.png"))) {

  private var progress: Float = 1

  setHeight(3)
  setWidth(50)
  var angle = 0

  override def draw(batch: Batch, parentAlpha: Float) {
    setOrigin(getWidth / 2, 0)
    setPosition(getParent.getWidth / 2 - getWidth / 2, 13)
    setScaleX(math.sin(angle * math.Pi / 180f).toFloat)
    if (!getParent.asInstanceOf[Target].front)
      super.draw(batch, parentAlpha)
  }

  override def act(delta: Float) {
    angle = angle + 1
    //progress += .1f
  }
}
