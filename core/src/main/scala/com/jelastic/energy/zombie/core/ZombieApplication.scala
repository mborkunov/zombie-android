package com.jelastic.energy.zombie.core

import com.badlogic.gdx.graphics._
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.{Actor, Stage}
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.jelastic.energy.zombie.core.actors.{Target, Overlay, Grid, Background}

class ZombieApplication extends ApplicationListener {

  private var stage: Stage = null
  //var score: Int = 0
  //var started: Boolean = false
  //var highScore: Int = 0

  lazy val width: Int = Gdx.graphics.getWidth
  lazy val height: Int = Gdx.graphics.getHeight
  lazy val layout:Layout = new Layout(width, height)
  lazy val spriteBatch: SpriteBatch = new SpriteBatch


  def create() {
    stage = new Stage(width, height, true, spriteBatch)
    stage.addActor(new Background)

    for (col <- Range.apply(0, layout.cols)) {
      for (row <- Range.apply(0, layout.rows)) {
        val target: Target = new Target(layout, col, row)
        stage.addActor(target)
      }
    }
    stage.addActor(new Overlay(layout))
    stage.addActor(new Grid)
    Gdx.input.setInputProcessor(stage)
  }

  def render() {
    Gdx.gl.glClearColor(0, 0, 0, 0)
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT)
    stage.act(Gdx.graphics.getDeltaTime)
    stage.draw()
  }

  def resize (width: Int, height: Int) {
    stage.setViewport(width, height, true)
    layout.resize(width, height)
  }

  def pause() {}

  def resume() {}

  def dispose() = stage.dispose()

}

