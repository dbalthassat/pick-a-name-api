package com.dbalthassat.utils;

import com.dbalthassat.entity.PersonOfEvent;
import com.dbalthassat.entity.Person;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class FriendshipUtilsTest {
	@Test(expected = NullPointerException.class)
	public void nullFriendship() {
		FriendshipUtils.findFriend(null, null);
	}

	@Test(expected = IllegalStateException.class)
	public void emptyFriendship() {
		FriendshipUtils.findFriend(new PersonOfEvent(null, new Person("toto")), Collections.emptyList());
	}

	@Test(expected = IllegalStateException.class)
	public void lonelyMan() {
		Set<PersonOfEvent> set = new HashSet<>();
		set.add(new PersonOfEvent(null, new Person("toto")));
		FriendshipUtils.findFriend(new PersonOfEvent(null, new Person("toto")), set);
	}

	@Test
	public void couple() {
		Set<PersonOfEvent> set = new HashSet<>();
		set.add(new PersonOfEvent(null, new Person("toto")));
		set.add(new PersonOfEvent(null, new Person("tata")));
		assertEquals("tata", FriendshipUtils.findFriend(new PersonOfEvent(null, new Person("toto")), set).getName());
		assertEquals("toto", FriendshipUtils.findFriend(new PersonOfEvent(null, new Person("tata")), set).getName());
	}

	@Test
	public void three() {
		for(int i = 0; i < 1000; ++i) {
			Set<PersonOfEvent> set = new HashSet<>();
			PersonOfEvent toto = new PersonOfEvent(null, new Person("toto"));
			PersonOfEvent tata = new PersonOfEvent(null, new Person("tata"));
			PersonOfEvent titi = new PersonOfEvent(null, new Person("titi"));
			set.add(toto);
			set.add(tata);
			set.add(titi);
			Person friendToto = FriendshipUtils.findFriend(toto, set);
			Person friendTata = FriendshipUtils.findFriend(tata, set);
			Person friendTiti = FriendshipUtils.findFriend(titi, set);
			assertNotEquals("toto", friendToto.getName());
			assertNotEquals("tata", friendTata.getName());
			assertNotEquals("titi", friendTiti.getName());
			assertNotEquals(friendToto, friendTata);
			assertNotEquals(friendTata, friendTiti);
			assertNotEquals(friendTiti, friendToto);
		}
	}

	@Test
	public void bigEvent() {
		playFriendshipBetweenEveryone(100, 1000);
	}

	private List<Set<PersonOfEvent>> playFriendshipBetweenEveryone(int pplNumber, int loops) {
		List<Set<PersonOfEvent>> sets = new LinkedList<>();
		for(int i = 0; i < loops; ++i) {
			Set<PersonOfEvent> list = new HashSet<>();
			for(int j = 0; j < pplNumber; ++j) {
				list.add(new PersonOfEvent(null, new Person("guy"+j)));
			}
			assertEquals(pplNumber, list.size());
			for(PersonOfEvent ep: list) {
				FriendshipUtils.findFriend(ep, list);
			}
			Set<String> checkingListFriend = new HashSet<>();
			for(PersonOfEvent ep: list) {
				assertNotEquals(ep.getPerson().getName(), ep.getFriend().getName());
				checkingListFriend.add(ep.getFriend().getName());
			}
			assertEquals(pplNumber, checkingListFriend.size());
			sets.add(list);
		}
		return sets;
	}

	@Test
	public void testRandomness() {
		int testNumber = 100;
		int loops = 10;
		int pplNumber = 100;
		int[] counts = new int[testNumber];
		for(int i = 0; i < testNumber; ++i) {
			List<Set<PersonOfEvent>> sets = playFriendshipBetweenEveryone(pplNumber, loops);
			int count = 0;
			for(Set<PersonOfEvent> set: sets) {
				for(Set<PersonOfEvent> compare: sets) {
					if(set != compare) {
						for(PersonOfEvent ep: set) {
							for(PersonOfEvent epCompare: compare) {
								if(ep.getPerson().equals(epCompare.getPerson())) {
									if(ep.getFriend().equals(epCompare.getFriend())) {
										count++;
									}
								}
							}
						}
					}
				}
			}
			counts[i] = count;
		}
		double average = Arrays.stream(counts).average().getAsDouble();
		assertTrue("Our algorithm is not enough randomness (average=" + average + " and loops * pplNumber=" + loops * pplNumber + ")", average < loops * pplNumber);
	}
}
