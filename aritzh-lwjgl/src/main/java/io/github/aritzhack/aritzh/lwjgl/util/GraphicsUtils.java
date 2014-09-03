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

package io.github.aritzhack.aritzh.lwjgl.util;

/**
 * @author Aritz Lopez
 */
public class GraphicsUtils {

    public static int ARGBtoRGBA(int argb) {
        int a = (argb & 0xFF000000) >>> 24;
        System.out.println(Integer.toHexString(a));
        int ret = argb << 8;
        return ret | a;
    }
}