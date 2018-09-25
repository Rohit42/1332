import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Rohit Mittapalli
 * @userid rmittapalli3
 * @GTID 903309727
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        int size = arr.length;
        boolean clean;
        for (int i = 0; i < size - 1; i++) {
            clean = true;
            for (int j = 1; j < size - i; j++) {
                if (comparator.compare(arr[j], arr[j - 1]) < 0) {
                    clean = false;
                    swap(arr, j, j - 1);
                }
            }
            if (clean) {
                return;
            }
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        int size = arr.length;
        for (int i = 1; i < size; i++) {
            for (int j = i; j > 0 && comparator.compare(arr[j],
                    arr[j - 1]) < 0; j--) {
                swap(arr, j, j - 1);

            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        int size = arr.length;
        for (int i = 0; i < size - 1; i++) {
            int index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[index]) <= 0) {
                    index = j;
                }
            }
            swap(arr, i, index);
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Random cannot be null");
        }
        recursiveQuickSort(arr, 0, arr.length - 1, rand, comparator);
    }

    /** Recursive helper function for the quicksort
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param startIndex the start index of the current pivot
     * @param endIndex the end index of the current pivot
     */
    public static <T> void recursiveQuickSort(T[] arr, int startIndex,
                    int endIndex, Random rand, Comparator comparator) {
        if (startIndex < endIndex) {
            int pivot = partition(arr, startIndex, endIndex, rand, comparator);
            recursiveQuickSort(arr, startIndex, pivot - 1, rand, comparator);
            recursiveQuickSort(arr, pivot + 1, endIndex, rand, comparator);
        }
    }


    /** Helper function to partition the array
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param a the start
     * @param b the end
     * @return the current pivot
     */
    public static <T> int partition(T[] arr,
                           int a, int b, Random rand, Comparator comparator) {
        int randomIndex = rand.nextInt(b - a) + a;
        swap(arr, b, randomIndex);

        T pivotValue = arr[b];
        int pivotIndex = a;

        for (int j = a; j < b; j++) {
            if (comparator.compare(arr[j], pivotValue) < 0) {
                swap(arr, pivotIndex, j);
                pivotIndex = pivotIndex + 1;
            }
        }
        swap(arr, pivotIndex, b);

        return pivotIndex;
    }




    /** Helper function to swap elements
     * @param <T> data type to sort
     * @param arr the array to be swapped
     * @param first index to be swapped
     * @param second index to be swapped
     */
    public static <T> void swap(T[] arr, int first, int second) {
        T temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        T[] temp = mergeRecursive(arr, comparator);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
    }
    /**
     * Helper function for merge
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @return T[]
     */
    public static <T> T[] mergeRecursive(T[] arr, Comparator<T> comparator) {
        int size = arr.length;
        if (size <= 1) {
            return arr;
        }
        T[] a = (T[]) new Object[size / 2];
        T[] b = (T[]) new Object[size - size / 2];
        for (int i = 0; i < a.length; i++) {
            a[i] = arr[i];
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = arr[i + size / 2];
        }
        return merge(mergeRecursive(a, comparator),
                mergeRecursive(b, comparator), comparator);
    }
    /**
     * Helper function for merge
     * @param <T> data type to sort
     * @param a the array to be merged
     * @param b the array to be merged
     * @param comparator the Comparator used to compare the data in arr
     * @return T[]
     */
    private static <T> T[] merge(T[] a, T[] b, Comparator comparator) {
        T[] c = (T[]) new Object[a.length + b.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < c.length; k++) {
            if (i >= a.length) {
                c[k] = b[j++];
            } else if (j >= b.length) {
                c[k] = a[i++];
            } else if (comparator.compare(a[i], b[j]) <= 0) {
                c[k] = a[i++];
            } else {
                c[k] = b[j++];
            }
        }
        return c;
    }
    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        if (arr.length <= 1) {
            return;
        }
        List<Integer>[] buckets = new ArrayList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (Math.abs(arr[i]) > max) {
                max = Math.abs(arr[i]);
            }
        }
        for (int divisor = 1; max / divisor > 0; divisor *= 10) {

            for (int j = 0; j < arr.length; j++) {
                System.out.println(divisor);
                int temp = (arr[j] / divisor) % 10;
                buckets[temp + 9].add(arr[j]);

            }
            int a = 0;

            for (int j = 0; j < 19; j++) {
                for (int k = 0; k < buckets[j].size(); k++) {
                    arr[a] = buckets[j].get(k);
                    a++;
                }
                buckets[j] = new ArrayList<>();
            }
        }
    }
}
