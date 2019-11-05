package org.interestTeam.model2.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class LambdaTest {

	@Test
	public void s(){
		String[] players = {"zhansgan", "lisi", "wangwu", "zhaoliu",  "wangmazi"};
		Arrays.sort(players, (String s1, String s2) ->  (s1.length() - s2.length()));
		List<String> list = (List<String>) Arrays.asList(players);
		list.forEach(value -> System.out.println(value));
		System.out.println("==========");
		/*List*/
//		Stream<String> _stream = list.stream().filter( e -> e.length() > 4 ).limit(1);
//		list = _stream.collect(Collectors.<String>toList());
//		list.forEach(System.out::println);
		Optional<String> _stream = list.stream().filter( e -> (e.length() > 4 && e.indexOf("z")!=-1) ).findFirst();
		if (_stream.isPresent())
			System.out.println(_stream.get());
	}
}
