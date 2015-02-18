package com.payment_cracker.api.dao.high_level.generate_names;

public interface NameGenerator {
	Name generate(Gender gender);
	Name generate(Gender gender, Name mother, Name father);
}
