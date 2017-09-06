package org.gvozdetscky.logic.sort.merge;

import org.gvozdetscky.logic.sort.Sort;

/**
 * Created by Egor on 06.09.2017.
 */
public class BubbleSort implements Sort {
	@Override
	public int[] sort(int[] a) {
		return bubbleSort(a);
	}

	private int[] bubbleSort(int[] a) {
		int temp;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		return a;
	}
}
