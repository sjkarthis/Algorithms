package com.my.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class TopologicalSort1 {
	public static void main(String a[]) {
		int[][] graph = { { 1, 3 }, { 1, 2 }, { 3, 4 }, { 5, 6 }, { 6, 3 }, { 3, 8 }, { 8, 11 } };
		HashSet<Integer> oset = new HashSet<Integer>();
		HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < graph.length; i++) {
			if (map.containsKey(graph[i][1])) {
				map.get(graph[i][1]).add(graph[i][0]);
			} else {
				ArrayList<Integer> dep = new ArrayList<>();
				dep.add(graph[i][0]);
				map.put(graph[i][1], dep);
			}
			oset.add(graph[i][0]);
			oset.add(graph[i][1]);
		}
		Set<Integer> ord = new HashSet<>();
		Iterator<Integer> itr = oset.iterator();
		while (itr.hasNext()) {
			findDependency(itr.next(), map, ord);
		}
		System.out.println(ord);
	}

	private static void findDependency(int key, HashMap<Integer, List<Integer>> map, Set<Integer> ord) {
		if (!map.containsKey(key) || ord.containsAll(map.get(key))) {
			if (!ord.contains(key)) {
				ord.add(key);
				System.out.println(key);
			}
			return;
		}
		for (int i : map.get(key)) {
			findDependency(i, map, ord);
			if (!map.containsKey(key) || ord.containsAll(map.get(key))) {
				if (!ord.contains(key)) {
					ord.add(key);
					System.out.println(key);
				}
			}
		}
	}
}
