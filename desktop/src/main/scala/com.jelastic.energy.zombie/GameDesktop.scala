package com.jelastic.energy.zombie

import com.badlogic.gdx.Files
import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.jelastic.energy.zombie.core.Application

object GameDesktop extends App {
    val config: LwjglApplicationConfiguration = new LwjglApplicationConfiguration
    config.useGL30 = false
    config.width = 1280
    config.height = 800
    config.fullscreen = false
    config.vSyncEnabled = true

    config.addIcon("gfx/logo.png", Files.FileType.Internal)
    config.title = "Hit the Zombie"
    new LwjglApplication(new Application, config)
}


