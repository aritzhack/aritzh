/*
 * Copyright 2014 Aritz Lopez
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.aritzhack.aritzh.slick2d;

import io.github.aritzhack.aritzh.logging.ILogger;
import io.github.aritzhack.aritzh.logging.SLF4JLogger;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

/**
 * @author Aritz Lopez
 */
public abstract class Game extends StateBasedGame {

    public static final ILogger LOG = new SLF4JLogger("ShootAndLoot");
    public static Font FNT_VERDANA_18;
    public static Font FNT_VERDANA_24;
    private GameContainer container;

    /**
     * Create a new state based game
     */
    public Game(String name) {
        super(name);
    }

    @Override
    public final void initStatesList(GameContainer container) throws SlickException {
        this.container = container;
        FNT_VERDANA_18 = new TrueTypeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.PLAIN, 18), true);
        FNT_VERDANA_24 = new TrueTypeFont(new java.awt.Font("Comic Sans MS", java.awt.Font.BOLD, 24), true);
        this.initStates();
    }

    public abstract void initStates() throws SlickException;

    public GameContainer getGC() {
        return this.container;
    }
}
