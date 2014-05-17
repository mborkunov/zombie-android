package com.jelastic.energy.zombie

import com.jelastic.energy.zombie.core.ZombieApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.{Files, Gdx}

object GameDesktop extends App {
    val config: LwjglApplicationConfiguration = new LwjglApplicationConfiguration
    config.useGL20 = true
    config.width = 1920
    config.height = 1280
    config.fullscreen = false
    config.vSyncEnabled = true

    config.addIcon("gfx/logo.png", Files.FileType.Internal)
    config.title = "Hit the Zombie"
    new LwjglApplication(new ZombieApplication, config)
}


