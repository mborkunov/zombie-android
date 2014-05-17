package com.jelastic.energy.zombie.core

import com.jelastic.energy.zombie.core.actors.Target

class Layout(var width: Int, var height: Int, var cols: Int = 6, var rows: Int = 4) {

  var offset: (Int, Int) = null
  var delta: (Int, Int) = null
  var textOffset: (Int, Int) = null
  var size: Int = 0
  var fontHeight: Int = 0

  calculate()

  def calculate() {
    size = getTargetSize
    delta = getDelta
    offset = getOffset(size, delta)
    fontHeight = getFontHeight
    textOffset = getTextOffset
  }

  def getFontHeight: Int = math.round(height / 15f)
  def getDelta: (Int, Int) = (math.round(width / 35f), math.round(height / 50f))
  def getTextOffset: (Int, Int) = (math.round(height / 15f), math.round(height / 20f))
  def getTargetSize: Int = math.round(height / 5.2f)

  def getOffset(size: Int, delta: (Int, Int)): (Int, Int) = {
    ((width - cols * size - (cols - 1)  * delta._1) / 2,
    /*height - ((targetSize + targetDeltaY) * Layout.ROWS) - */math.round(height * .1f))
  }

  def setTargetLocation(target: Target, col: Int, row: Int) {
    val realWidth = target.sprite().getRegionWidth
    val spriteX: Int = col * (size + delta._1) + offset._1 + size / 2 - realWidth / 2
    val spriteY: Int = row * (size + delta._2) + offset._2 + size / 2 - realWidth / 2
    target.sprite().setPosition(spriteX, spriteY)
  }

  def resize(width: Int, height: Int) {
    this.width = width
    this.height = height
  }

  def setSize(cols: Int, rows: Int) {
    this.cols = cols
    this.rows = rows
    calculate()
  }

  override def toString: String = {
    new StringBuilder("Layout{")
      //.append("offset: ").append(offset).append(", ")
      .append("width: ").append(width).append(", ")
      .append("height: ").append(height).append(", ")
      .append("target delta: ").append(delta).append(", ")
      .append("target size: ").append(size).append(", ")
      .append("text offset: ").append(textOffset).append(", ")
      .append("font height: ").append(fontHeight).append("}")
      .toString()
  }
}