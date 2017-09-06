package org.gvozdetscky.logic.sort;

import org.gvozdetscky.logic.sort.merge.BubbleSort;
import org.gvozdetscky.logic.sort.merge.MergeSort;
import org.junit.*;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Egor on 06.09.2017.
 */
public class SpeedSortTest {

	private int[] a;
	private DateFormat formatter;
	private Sort sort;

	@Before
	public void afterTest() {
		formatter = new SimpleDateFormat("HH:mm:ss");
		a = new int[100000];
		System.out.println("Generate... " + formatter.format(new Date()));
		a = populate(a);
		System.out.println("Generate Done: " + formatter.format(new Date()));
	}

	@Test
	public void SpeedMergeTest() {
		long beforeMergeSortLong = new Date().getTime();
		System.out.println("Start merge sort..." + formatter.format(new Date()));

		sort = new MergeSort();
		sort.sort(a);

		long afterMergeSortLong = new Date().getTime();
		System.out.println("Finish merge sort..." + formatter.format(new Date()));
		System.out.println("Затрачено времени... " + (afterMergeSortLong - beforeMergeSortLong) / 1000 + " сек.");
	}

	@Test
	public void SpeedBubbleSort() {
		long beforeBubbleSortLong = new Date().getTime();
		System.out.println("Start bubble sort..." + formatter.format(new Date()));

		sort = new BubbleSort();
		sort.sort(a);

		long afterBubbleSortLong = new Date().getTime();
		System.out.println("Finish bubble sort..." + formatter.format(new Date()));
		System.out.println("Затрачено времени... " + (afterBubbleSortLong - beforeBubbleSortLong) / 1000 + " сек.");
	}

	private static int[] populate(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 100);
		}
		return a;
	}

}