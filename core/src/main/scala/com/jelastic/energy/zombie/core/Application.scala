package com.jelastic.energy.zombie.core

import com.badlogic.gdx.graphics._
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.{FitViewport, Viewport}
import com.badlogic.gdx.{ApplicationListener, Gdx, Preferences}
import com.jelastic.energy.zombie.core.actors._

class Application extends ApplicationListener {

  private var stage: Stage = null

  lazy val preferences:Preferences = Gdx.app.getPreferences("Preferences")
  lazy val viewport: Viewport = new FitViewport(Gdx.graphics.getWidth, Gdx.graphics.getHeight)
  lazy val game: Game = new Game(viewport)

  def create() {
    stage = new Stage(viewport, new SpriteBatch)
    stage.addActor(game)
    Gdx.input.setInputProcessor(stage)
    stage.setDebugUnderMouse(true)
  }

  def render() {
    Gdx.gl.glClearColor(0, 0, 0, 0)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    stage.act(Gdx.graphics.getDeltaTime)
    stage.draw()
  }

  def resize (width: Int, height: Int) {
    viewport.update(width, height)
    /*layout.calculate()
    for (actor: Actor <- stage.getActors.items) {
      actor match {
        case b: BattleField =>
          val children: SnapshotArray[Actor] = b.getChildren
          for (c <- children.begin()) {
            c match {
              case t: Target => layout.setTargetLocation(t)
              case _ =>
            }
          }
          children.end()
        case _ =>
      }
    }*/
  }

  def dispose() = stage.dispose()

  override def pause(): Unit = {}

  override def resume(): Unit = {}
}

