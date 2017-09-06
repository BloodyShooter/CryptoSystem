package org.gvozdetscky.logic.sort.merge;

import org.gvozdetscky.logic.sort.Sort;

/**
 * Created by Egor on 05.09.2017.
 */
public class MergeSort implements Sort {
	@Override
	public int[] sort(int[] a) {
		return merge_sort(a);
	}

	private int[] merge_sort(int[] a) {

		if (a.length <= 1) {
			return a;
		}

		int midpoint = a.length / 2;

		int[] left = new int[midpoint];
		int[] right;

		if (a.length % 2 == 0) {
			right = new int[midpoint];
		} else {
			right = new int[midpoint + 1];
		}

		System.arraycopy(a, 0, left, 0, midpoint);

		for (int j = midpoint, x = 0; j < a.length; j++) {
			if (x < right.length) {
				right[x] = a[j];
				x++;
			}
		}

		left = merge_sort(left);
		right = merge_sort(right);

		return merge(left, right);
	}

	private int[] merge(int[] left, int[] right) {
		int lengthResult = left.length + right.length;
		int[] result = new int[lengthResult];
		int indexL = 0;
		int indexR = 0;
		int indexRes = 0;

		while (indexL < left.length || indexR < right.length) {
			if (indexL < left.length && indexR < right.length) {
				if (left[indexL] <= right[indexR]) {
					result[indexRes] = left[indexL];
					indexL++;
					indexRes++;
				} else {
					result[indexRes] = right[indexR];
					indexR++;
					indexRes++;
				}
			} else if (indexL < left.length) {
				result[indexRes] = left[indexL];
				indexL++;
				indexRes++;
			} else if (indexR < right.length) {
				result[indexRes] = right[indexR];
				indexR++;
				indexRes++;
			}
		}

		return result;
	}
}
