public class ShellSort {
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int len = arr.length;
        int gap = arr.length / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                int tmp = arr[i];
                int j = i - gap;
                // 如果向前推一个gap的数大于tmp
                while (j >= 0 && arr[j] > tmp) {
                    arr[j+gap] = arr[j];
                    j -= gap;
                }
                arr[j+gap] = tmp;
            }
            gap = gap / 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 90, 5, 12, 40, 55};
        shellSort(arr);
        for (int num : arr) {
            System.out.print(num + "  ");
        }
    }
}
