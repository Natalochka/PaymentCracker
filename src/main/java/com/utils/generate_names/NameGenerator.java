package com.utils.generate_names;

public interface NameGenerator {
	Name generate(Gender gender);
	Name generate(Gender gender, Name mother, Name father);
}
