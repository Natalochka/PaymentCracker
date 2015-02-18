package com.utils.generate_names;

public class NameGenerators {
	public static final CompositeNameGenerator standardGenerator() {
		return CompositeNameGenerator
				.newGenerator()
				.withGenerator("englishlike", new EnglishlikeNameGenerator(), 1);
	}
}
