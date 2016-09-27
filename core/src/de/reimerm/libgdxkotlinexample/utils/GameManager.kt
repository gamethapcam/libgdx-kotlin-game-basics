/*
 * Copyright (c) 2016. Marius Reimer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.reimerm.libgdxkotlinexample.utils

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Actor
import de.reimerm.libgdxkotlinexample.enums.GameState
import de.reimerm.libgdxkotlinexample.menu.GameMenu
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Singleton to manage game relevant objects.
 *
 * Created by Marius Reimer on 21-Jun-16.
 */
object GameManager {
    lateinit var bodiesToRemove: Set<Body>
    lateinit var actorsToAdd: Set<Actor>

    var listener: GameEventListener? = null
        @Synchronized get

    lateinit var world: World
        @Synchronized get

    lateinit var gameState: GameState
        @Synchronized get
        @Synchronized set

    var menu: GameMenu? = null

    fun reset() {
        gameState = GameState.RUNNING
        bodiesToRemove = Collections.newSetFromMap(ConcurrentHashMap<Body, Boolean>())
        actorsToAdd = Collections.newSetFromMap(ConcurrentHashMap<Actor, Boolean>())
        menu = null
    }

    @Synchronized
    fun addBodyToRemove(body: Body) {
        bodiesToRemove = bodiesToRemove.plus(body)
    }

    @Synchronized
    fun addActorToAdd(actor: Actor) {
        actorsToAdd = actorsToAdd.plus(actor)
    }

    @Synchronized
    fun onPause() {
        if (gameState == GameState.RUNNING) {
            gameState = GameState.PAUSED
            AudioUtils.stopMusic()
        }
    }

    @Synchronized
    fun destroyBody(body: Body) {

        // if, for some reason, the body is already destroyed
        if (body.userData == null || body.fixtureList.size == 0) {
            bodiesToRemove = bodiesToRemove.minus(body)
            return
        }

        // for some reason there there was a non reproducible null pointer exception thrown
        try {
            world.destroyBody(body)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        bodiesToRemove = bodiesToRemove.minus(body)
    }
}