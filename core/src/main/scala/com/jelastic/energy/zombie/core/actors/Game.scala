package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.utils.Timer
import com.badlogic.gdx.utils.Timer.Task
import com.badlogic.gdx.utils.viewport.Viewport
import com.jelastic.energy.zombie.core.Audio

class Game(viewport: Viewport) extends Group {

  var _score: Int = 0
  var _highscore: Int = 0
  val ROUND_TIME: Int = 60 // seconds

  var running: Boolean = false

  def score = _score
  def score_= (value: Int) = _score = value

  def highscore = _highscore
  def highscore_= (value: Int) = _highscore = value


  lazy val layout:Layout = new Layout(viewport, 5, 3)
  lazy val overlay: Overlay = new Overlay(layout, this)

  addActor(new Background)
  val battleField: BattleField = new BattleField(layout)
  addActor(battleField)
  addActor(overlay)


  def start() {
    running = true
    Timer.schedule(new Task {
      override def run() = stop()
    }, ROUND_TIME)
    overlay.setVisible(false)
    Audio.BUTTON.play()
  }

  def stop() {
    running = false
    overlay.setVisible(true)
  }

  override def act(delta: Float) {
    battleField.doStrategy(running)
    super.act(delta)
  }


}

