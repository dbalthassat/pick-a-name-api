package com.dbalthassat.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SlugUtilsTest {
	@Test(expected = NullPointerException.class)
	public void createSlugWithNull() {
		SlugUtils.createSlug(null);
	}

	@Test
	public void createSlugWithEmptyString() {
		assertEquals("", SlugUtils.createSlug(""));
	}

	@Test
	public void createSlugWithBlankCharacterStringOnly() {
		assertEquals("", SlugUtils.createSlug(" \t    \t"));
	}

	@Test
	public void createSlugWithSimpleString() {
		assertEquals("toto", SlugUtils.createSlug("toto"));
	}

	@Test
	public void createSlugWithTwoWords() {
		assertEquals("one-two", SlugUtils.createSlug("one two"));
	}

	@Test
	public void createSlugWithMultipleBlankCharacters() {
		assertEquals("one-two", SlugUtils.createSlug("  one  \ttwo "));
	}

	@Test
	public void createSlugWithDigits() {
		assertEquals("2-oranges", SlugUtils.createSlug("2 oranges"));
	}

	@Test
	public void createSlugWithAccents() {
		assertEquals("and-voila", SlugUtils.createSlug("and voilà"));
	}

	@Test
	public void createSlugWithUppercase() {
		assertEquals("i-love-android", SlugUtils.createSlug("I love Android"));
	}

	@Test
	public void createSlugWithSpecialCharacters() {
		assertEquals("i-dont-understand", SlugUtils.createSlug("I don't understand"));
	}

	@Test
	public void createSlugWithStartingWithHyphen() {
		assertEquals("i-dont-understand", SlugUtils.createSlug("-I don't understand"));
	}
}
