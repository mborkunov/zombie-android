package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx

class Background extends Image(new Texture(Gdx.files.internal("gfx/zombie/background.png"))) {
  val sx:Float = Gdx.graphics.getWidth / getWidth
  val sy:Float = Gdx.graphics.getHeight / getHeight
  setScale(sx, sy)
}
