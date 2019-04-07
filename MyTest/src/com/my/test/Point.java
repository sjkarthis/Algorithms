package com.my.test;

import java.util.TreeMap;

public class Point {
	int x, y;
	TreeMap<Double, Point> pointMap;
	Double totalDistance = 0.0;

	Point(int x, int y) {
		pointMap = new TreeMap<Double, Point>();
		this.x = x;
		this.y = y;
	}

	public static double diff(Point a, Point b) {
		double difference = Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
		return difference;
	}

	public void addPoint(Double diff, Point p) {
		pointMap.put(diff, p);
	}

	public void setTotalDistance(int numDeliveries) {
		int counter = 0;
		for (Double key : pointMap.keySet()) {
			if (counter < numDeliveries)
				this.totalDistance = this.totalDistance + key;
			else
				break;
			counter++;
		}
	}

	public static void main(String[] args) {
		int numDestinations = 10;
		int[][] allLocations = { { 0, 0 }, { 3, 4 }, { 1, -1 }, { 3, 2 }, { 5, 4 }, { 4, -1 }, { -1, 2 }, { 3, 6 },
				{ 1, -6 }, { 2, 4 } };
		int numDeliveries = 4;
		Point startPoint = null;
		for (int i = 0; i < numDestinations; i++) {
			Point root = new Point(allLocations[i][0], allLocations[i][1]);
			int counter = 0;
			while (counter < numDestinations) {
				if (i != counter) {
					Point child = new Point(allLocations[counter][0], allLocations[counter][1]);
					root.addPoint(Point.diff(root, child), child);
				}
				counter++;
			}
			root.setTotalDistance(numDeliveries);
			System.out.println("------------------------");
			System.out.println("Node :" + root);
			System.out.println("------------------------");
			System.out.println("Total Distance :" + root.totalDistance);
			for (Double key : root.pointMap.keySet()) {
				System.out.println(root.pointMap.get(key) + "-" + key);
			}
			System.out.println("------------------------");

			startPoint = (startPoint == null) ? root
					: (startPoint.totalDistance > root.totalDistance) ? root : startPoint;
		}
		System.out.println("------------------------");
		System.out.println("------------------------");
		System.out.println("Best root :" + startPoint);
		System.out.println("------------------------");
		System.out.println("------------------------");
		

	}

	public String toString() {
		return "{" + x + "," + y + "}";
	}
}
