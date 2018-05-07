package sortingalgorithms;

import java.util.Arrays;

public class MainClass {

    private static final int SIZE_ARRAY = 200;
    private static final int RANGE_VALUES = 1000;

    //Сортировка пузырьком
    private static <E extends Comparable<E>> E[] sortBubble(E[] e) {
        if (e == null) return null;

        int compareCount = 0;        //Счетчик сравнений
        int assignCount = 0;         //Счетчик присваиваний

        boolean bitSwap = false;     //Равен true, если в результате прохода по массиву была выполнена хоть одна перестановка
        E tmp;                     //Переменная для временного хранения перемещаемых элементов

        for (int i = (e.length - 1); i > 0; i--) {
            bitSwap = false;
            for (int j = 1; j <= i; j++) {
                if (e[j - 1].compareTo(e[j]) == 1) {
                    compareCount++;
                    tmp = e[j - 1];
                    e[j - 1] = e[j];
                    e[j] = tmp;
                    assignCount += 3;
                    bitSwap = true;
                    continue;
                }
                compareCount++;
            }
            if (!bitSwap) break;
        }

        //Выводим статистику по работе алгоритма
        System.out.println();
        System.out.println("Сортировка пузырьком");
        System.out.println("    выполнено сравнений: " + compareCount);
        System.out.println("    выполнено присваиваний: " + assignCount);
        System.out.println("    общее количество операций: "+(compareCount+assignCount));

        return e;
    }

    //Сортировка методом выбора
    private static <E extends Comparable<E>> E[] sortSelect(E[] e) {
        if (e == null) return null;

        int compareCount = 0;        //Счетчик сравнений
        int assignCount = 0;         //Счетчик присваиваний

        int posMin;                //Указатель на элемент с минимальным значением
        E tmp;                     //Переменная для временного хранения перемещаемых элементов

        for (int i = 0; i < (e.length - 1); i++) {
            posMin = i;
            for (int j = (i + 1); j < e.length; j++) {
                if (e[j].compareTo(e[posMin]) == (-1)) {
                    compareCount++;
                    posMin = j;
                    continue;
                }
                compareCount++;
            }
            if (posMin != i) {
                tmp = e[i];
                e[i] = e[posMin];
                e[posMin] = tmp;
                assignCount += 3;
            }
        }

        //Выводим статистику по работе алгоритма
        System.out.println();
        System.out.println("Сортировка методом выбора");
        System.out.println("    выполнено сравнений: " + compareCount);
        System.out.println("    выполнено присваиваний: " + assignCount);
        System.out.println("    общее количество операций: "+(compareCount+assignCount));

        return e;
    }

    //Сортировка вставками
    private static <E extends Comparable<E>> E[] sortInsert(E[] e) {
        if (e == null) return null;

        int compareCount = 0;        //Счетчик сравнений
        int assignCount = 0;         //Счетчик присваиваний

        int sizeSortedArea = 1;    //Первоначальный размер отсортированной области
        E tmp;                     //Переменная для временного хранения перемещаемых элементов
        int i;                     //Вспомогательная переменная-счетчик

        //Продолжаем перестановки до тех пор, пока размер отсортированной области не дорстигнет размеров массива
        while (sizeSortedArea < e.length) {
            tmp = e[sizeSortedArea];
            assignCount++;
            i = sizeSortedArea - 1;
            while (i >= 0 && tmp.compareTo(e[i]) == (-1)) {
                compareCount++;
                e[i + 1] = e[i];
                assignCount++;
                i--;
            }
            compareCount++;
            e[i + 1] = tmp;
            assignCount++;
            sizeSortedArea++;
        }

        //Выводим статистику по работе алгоритма
        System.out.println();
        System.out.println("Сортировка вставками");
        System.out.println("    выполнено сравнений: " + compareCount);
        System.out.println("    выполнено присваиваний: " + assignCount);
        System.out.println("    общее количество операций: "+(compareCount+assignCount));

        return e;
    }

    //Сортировка Шелла
    private static <E extends Comparable<E>> E[] sortShell(E[] e) {
        if (e == null) return null;

        int compareCount = 0;        //Счетчик сравнений
        int assignCount = 0;         //Счетчик присваиваний

        E tmp;                       //Переменная для временного хранения перемещаемых элементов
        int k;                       //Вспомогательная переменная-счетчик

        int h = 1;                     //Начальное значение интервала
        while (h <= (e.length / 3)) {
            h = h * 3 + 1;
        }

        //В самом внешнем цикле перебираем значения интервала
        while (h > 0) {

            //В этом цикле сдвигаем "интервальную рамку"
            for (int i = 0; i < h; i++) {

                //В этом цикле сортируем элементы в пределах одного интервала
                for (int j = (i + h); j < e.length; j += h) {
                    tmp = e[j];
                    assignCount++;
                    k = j - h;
                    while (k >= 0 && tmp.compareTo(e[k]) == -1) {
                        compareCount++;
                        e[k + h] = e[k];
                        assignCount++;
                        k -= h;
                    }
                    compareCount++;
                    e[k + h] = tmp;
                    assignCount++;
                }

            }

            h = (h - 1) / 3;
        }

        //Выводим статистику по работе алгоритма
        System.out.println();
        System.out.println("Сортировка Шелла");
        System.out.println("    выполнено сравнений: " + compareCount);
        System.out.println("    выполнено присваиваний: " + assignCount);
        System.out.println("    общее количество операций: "+(compareCount+assignCount));

        return e;
    }

    //Класс, реализующий быструю сортировку
    private static class QuickSort<E extends Comparable<E>> {

        private int compareCount = 0;        //Счетчик сравнений
        private int assignCount = 0;         //Счетчик присваиваний

        void sort(E[] e, int left, int right) {
            if (e == null) return;
            if (left>=right)return;
            int pos=getSplitPosition(e,left,right);
            sort(e,left,pos-1);
            sort(e,pos+1,right);
        }

        //Вспомогательный метод для быстрой сортировки
        private int getSplitPosition(E[] e, int left, int right) {
            E splitElement = e[right];
            E tmp;
            int l = left - 1;
            int r = right + 1;
            int pos;
            while (true) {
                while ((--r)>l && e[r].compareTo(splitElement)>=0){compareCount++;}
                if (r>l)compareCount++;
                while ((++l)<r && e[l].compareTo(splitElement)==(-1)){compareCount++;}
                if (l<r)compareCount++;
                if (r<=l)break;
                tmp=e[r];
                e[r]=e[l];
                e[l]=tmp;
                assignCount+=3;
            }
            pos=r+1;
            e[right]=e[pos];
            e[pos]=splitElement;
            assignCount++;
            return pos;
        }

        //Метод отображает статистику работы алгоритма
        void showStat(){
            System.out.println();
            System.out.println("Быстрая сортировка");
            System.out.println("    выполнено сравнений: " + compareCount);
            System.out.println("    выполнено присваиваний: " + assignCount);
            System.out.println("    общее количество операций: "+(compareCount+assignCount));
        }
    }

    //Вспомогательный метод для отображения массивов
    private static <E> void show(E[] e) {
        if (e == null) {
            System.out.println("[]");
        }
        System.out.print("[ ");
        for (E tmp : e) System.out.print(tmp + " ");
        System.out.println("]");
    }

    public static void main(String[] args) {
        Integer[] a0 = new Integer[SIZE_ARRAY];   //Исходный массив
        Integer[] a1 = new Integer[SIZE_ARRAY];   //Массив для сортировки пузырьком
        Integer[] a2 = new Integer[SIZE_ARRAY];   //Массив для сортировки выбором
        Integer[] a3 = new Integer[SIZE_ARRAY];   //Массив для сортировки вставками
        Integer[] a4 = new Integer[SIZE_ARRAY];   //Массив для сортировки Шелла
        Integer[] a5 = new Integer[SIZE_ARRAY];   //Массив для быстрой сортировки

        //Заполняем и выводим исходный массив
        for (int i = 0; i < SIZE_ARRAY; i++) a0[i] = new Integer((int) (Math.random() * RANGE_VALUES));
        System.out.println("Исходный числовой массив (целые числа):");
        show(a0);

        //Заполняем вспомогательные массивы, которые будем потом сортировать
        a1 = Arrays.copyOf(a0, SIZE_ARRAY);
        a2 = Arrays.copyOf(a0, SIZE_ARRAY);
        a3 = Arrays.copyOf(a0, SIZE_ARRAY);
        a4 = Arrays.copyOf(a0, SIZE_ARRAY);
        a5 = Arrays.copyOf(a0, SIZE_ARRAY);

        //Сортировка пузырьком
        a1 = sortBubble(a1);

        //Сортировка методом выбора
        a2 = sortSelect(a2);

        //Сортировка вставками
        a3 = sortInsert(a3);

        //Сортировка Шелла
        a4 = sortShell(a4);

        //Быстрая сортировка
        QuickSort<Integer> qs=new QuickSort<>();
        qs.sort(a5,0,a5.length-1);
        qs.showStat();

    }

}
