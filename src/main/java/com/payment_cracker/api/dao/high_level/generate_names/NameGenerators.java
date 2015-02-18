package com.payment_cracker.api.dao.high_level.generate_names;

public class NameGenerators {
	public static final CompositeNameGenerator standardGenerator() {
		return CompositeNameGenerator
				.newGenerator()
				.withGenerator("englishlike", new EnglishlikeNameGenerator(), 1);
	}
}
