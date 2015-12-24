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

package io.github.aritzhack.aritzh.awt.render;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import io.github.aritzhack.aritzh.awt.util.SpriteUtil;
import io.github.aritzhack.aritzh.util.Set2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Aritz Lopez
 */
public class SpriteSheetLoader {

	private static final Pattern spritePattern = Pattern.compile("(\\w+).png (\\d+) (\\d+) (\\d+) (\\d+)");

	public static SpriteSheet load(String shtFile) {
		Preconditions.checkArgument(shtFile != null, "File name cannot be null!");
		Preconditions.checkArgument(shtFile.length() != 0, "You must specify a valid path");

		InputStream is = SpriteSheetLoader.class.getClassLoader().getResourceAsStream(shtFile);
		Preconditions.checkArgument(is != null, "Could not find file %s", shtFile);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		try {
			String line = reader.readLine();
			if (line == null) throw new IllegalArgumentException("First line of the file could not be read");
			InputStream imageStream = SpriteSheetLoader.class.getClassLoader().getResourceAsStream(line.trim());


			BufferedImage sheet = ImageIO.read(imageStream);

			SpriteSheet sprites = new SpriteSheet();

			while ((line = reader.readLine()) != null) {
				Matcher matcher = spritePattern.matcher(line);
				if (!matcher.matches())
					throw new IllegalArgumentException("Line " + line + " does not match regex " + spritePattern.pattern());
				String name = matcher.group(1);
				int x = Integer.parseInt(matcher.group(2));
				int y = Integer.parseInt(matcher.group(3));
				int w = Integer.parseInt(matcher.group(4));
				int h = Integer.parseInt(matcher.group(5));
				sprites.put(name, SpriteSheetLoader.getSprite(sheet, x, y, w, h));
			}

			return sprites;
		} catch (IOException e) {
			throw new IllegalArgumentException("Error loading sprite sheet: " + shtFile, e);
		}
	}

	public static Sprite getSprite(BufferedImage image, int x, int y, int w, int h) {
		int[] pixels = image.getRGB(x, y, w, h, null, 0, w);
		return new Sprite(w, h, pixels);
	}

	public static class SpriteSheet extends HashMap<String, Sprite> {}
}
