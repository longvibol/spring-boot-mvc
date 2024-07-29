package com.piseth.java.school.phoneshopenight.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.piseth.java.school.phoneshopenight.utils.GeneralUtils;

public class GeneralUtilsTest {

	@Test
	public void testToIntegerList() {
		// Given
		List<String> names = List.of("Dara", "Cheata", "Thida");
		// When
		List<Integer> list = GeneralUtils.toIntegerList(names);
		// Then
		// [4,6,5]
		assertEquals(3, list.size());
		assertEquals(4, list.get(0));
		assertEquals(6, list.get(1));
		assertEquals(5, list.get(2));
	}

	@Test
	public void testGetEvenNumber() {
		// Given
		List<Integer> list = List.of(4, 6, 7, 8, 5, 40);

		// When
		List<Integer> evenNumber = GeneralUtils.getEvenNumber(list);

		// then
		assertEquals(4, evenNumber.size());
		assertEquals(4, evenNumber.get(0));
	}
}
