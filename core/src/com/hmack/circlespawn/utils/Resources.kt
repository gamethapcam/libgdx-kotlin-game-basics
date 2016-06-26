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

package com.hmack.circlespawn.utils

/**
 * All resources listed in an enum class.
 *
 * Created by Marius Reimer on 12-Jun-16.
 */
object Resources {
    val SPRITES_ATLAS_PATH = "sprites.atlas"

    enum class RegionNames(val str: String) {
        BACKGROUND_NAME("background"),
        BUTTON_PLAY_NAME("button_play"),
        BUTTON_PAUSE_NAME("button_pause"),
        BUTTON_RESUME_NAME("button_resume"),
        BUTTON_AGAIN_NAME("button_again"),
        BUTTON_QUIT_NAME("button_quit");
    }
}