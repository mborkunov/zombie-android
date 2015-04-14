package com.jelastic.energy.zombie.core.actors

import com.badlogic.gdx.utils.viewport.Viewport

class Layout(viewport: Viewport, var cols: Int = 6, var rows: Int = 4) {

  var offset: (Int, Int) = null
  var delta: (Int, Int) = null
  var textOffset: (Int, Int) = null
  var size: Int = 0
  var fontHeight: Int = 0

  var width: Int = viewport.getScreenWidth
  var height: Int = viewport.getScreenHeight

  calculate()

  def calculate() {
    width = viewport.getScreenWidth
    height = viewport.getScreenHeight
    size = getTargetSize
    delta = getDelta
    offset = getOffset(size, delta)
    fontHeight = getFontHeight
    textOffset = getTextOffset
  }

  def getFontHeight: Int = math.round(height / 15f)
  def getDelta: (Int, Int) = (math.round(width / 35f), math.round(height / 50f))
  def getTextOffset: (Int, Int) = (math.round(height / 15f), math.round(height / 20f))
  def getTargetSize: Int = (height + width) / cols * rows / 7

  def getOffset(size: Int, delta: (Int, Int)): (Int, Int) = {
    ((width - cols * size - (cols - 1)  * delta._1) / 2, (height - rows * size - (rows - 1)  * delta._2) / 4)
  }

  def setTargetLocation(target: Target) {
    val realWidth = target.sprite.getRegionWidth

    val x: Int = target.getCol * (size + delta._1) + offset._1 + size / 2 - realWidth / 2
    val y: Int = target.getRow * (size + delta._2) + offset._2 + size / 2 - realWidth / 2
    target.setPosition(x, y)
  }

  override def toString: String = {
    s"Layout{width: $width, height: $height, delta: $delta," +
      s" size: $size, text offset: $textOffset, font height: $fontHeight"
  }
}