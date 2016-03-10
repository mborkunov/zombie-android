package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.scenes.scene2d.Group


class BattleField(layout: Layout) extends Group {

  var targets: List[Target] = List()

  for (col <- 0 until layout.cols) {
    for (row <- 0 until layout.rows) {
      val target: Target = new Target(layout, col, row)
      layout.setTargetLocation(target)
      addActor(target)
      targets = targets :+ target
    }
  }

  def doStrategy(running: Boolean) {
    if (running) {
      // order to rotate random targets
      val sleeping: List[Target] = targets.filter(!_.rotating)

      sleeping.filter(_.front && math.random < .005).foreach(target => {
        target.rotate((math.random * 3).round.toInt)
      })
    } else {
      val active: List[Target] = targets.filter(p => p.rotating || p.front)
      //active.foreach(_)
      // close all active targets
    }
  }

}