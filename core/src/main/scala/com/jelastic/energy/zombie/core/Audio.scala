package com.jelastic.energy.zombie.core

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound

object Audio {

  val HIT: Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/zombie/hit.ogg"))
  val BUTTON: Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/zombie/button.ogg"))
  val FAIL: Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/zombie/fail.ogg"))

}
