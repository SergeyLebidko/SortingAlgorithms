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

    //Класс, реализующий простую быструю сортировку, без использования дополнительных методов сортировки и медианного выбора опорного значения
    private static class QuickSort1<E extends Comparable<E>> {

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
            assignCount+=2;
            return pos;
        }

        //Метод отображает статистику работы алгоритма
        void showStat(){
            System.out.println();
            System.out.println("Простая быстрая сортировка");
            System.out.println("    выполнено сравнений: " + compareCount);
            System.out.println("    выполнено присваиваний: " + assignCount);
            System.out.println("    общее количество операций: "+(compareCount+assignCount));
        }

        //Метод сбрасывает статистику работы алгоритма сортировки
        void clearStat(){
            compareCount=0;
            assignCount=0;
        }
    }

    //Класс, реализующий быструю сортировку с использованием сортировки вставками для малых подмассивов и медианным выбором опорного значения
    private static class QuickSort2<E extends Comparable<E>>{

        private int compareCount = 0;        //Счетчик сравнений
        private int assignCount = 0;         //Счетчик присваиваний

        void sort(E[] e, int left, int right) {
            if (e == null) return;
            if (left>=right)return;

            //Если массив содержит 10 и менее элементов - применяется сортировка вставками
            if ((right-left+1)<=10){
                E tmp;
                int j;
                for (int i=left;i<right;i++){
                    tmp=e[i+1];
                    assignCount++;
                    j=i;
                    while ((j>=left) && tmp.compareTo(e[j])==(-1)){
                        compareCount++;
                        e[j+1]=e[j];
                        assignCount++;
                        j--;
                    }
                    compareCount++;
                    e[j+1]=tmp;
                    assignCount++;
                }
                return;
            }

            int pos=getSplitPosition(e,left,right);
            sort(e,left,pos-1);
            sort(e,pos+1,right);
        }

        private int getSplitPosition(E[] e, int left, int right){
            //Выбираем медианное значение
            int center;              //Положение медианного элемента
            E splitElement;          //Переменная для хранения опорного (медианного) элемента
            E tmp;                   //Переменная для временнного хранения элементов
            center=(left+right)/2;

            //Сравниваем левое и центральное значения
            if (e[left].compareTo(e[center])==1){
                tmp=e[left];
                e[left]=e[center];
                e[center]=tmp;
                compareCount++;
                assignCount+=3;
            }
            //Сравниваем левое и правое значения
            if (e[left].compareTo(e[right])==1){
                tmp=e[left];
                e[left]=e[right];
                e[right]=tmp;
                compareCount++;
                assignCount+=3;
            }
            //Сравниваем центральное и правое значения
            if (e[center].compareTo(e[right])==1){
                tmp=e[center];
                e[center]=e[right];
                e[right]=tmp;
                compareCount++;
                assignCount+=3;
            }

            //Медианное значение получено
            splitElement=e[center];

            int l = left;
            int r = right;
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
                if (l==center){
                    center=r;
                    continue;
                }
            }
            pos=r+1;
            e[center]=e[pos];
            e[pos]=splitElement;
            assignCount+=2;
            return pos;

        }

        //Метод отображает статистику работы алгоритма
        void showStat(){
            System.out.println();
            System.out.println("Комбинация быстрой сортировки и сортировки вставками");
            System.out.println("    выполнено сравнений: " + compareCount);
            System.out.println("    выполнено присваиваний: " + assignCount);
            System.out.println("    общее количество операций: "+(compareCount+assignCount));
        }

        //Метод сбрасывает статистику работы алгоритма сортировки
        void clearStat(){
            compareCount=0;
            assignCount=0;
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
        Integer[] a5 = new Integer[SIZE_ARRAY];   //Массив для простой быстрой сортировки
        Integer[] a6 = new Integer[SIZE_ARRAY];   //Массив для комбинированной быстрой сортировки

        //Заполняем и выводим исходный массив
        for (int i = 0; i < SIZE_ARRAY; i++) a0[i] = new Integer((int) (Math.random() * RANGE_VALUES));
        System.out.println("Исходный числовой массив (целые числа, размер "+SIZE_ARRAY+"):");
        show(a0);

        //Заполняем вспомогательные массивы, которые будем потом сортировать
        a1 = Arrays.copyOf(a0, SIZE_ARRAY);
        a2 = Arrays.copyOf(a0, SIZE_ARRAY);
        a3 = Arrays.copyOf(a0, SIZE_ARRAY);
        a4 = Arrays.copyOf(a0, SIZE_ARRAY);
        a5 = Arrays.copyOf(a0, SIZE_ARRAY);
        a6 = Arrays.copyOf(a0, SIZE_ARRAY);

        //Сортировка пузырьком
        a1 = sortBubble(a1);

        //Сортировка методом выбора
        a2 = sortSelect(a2);

        //Сортировка вставками
        a3 = sortInsert(a3);

        //Сортировка Шелла
        a4 = sortShell(a4);

        //Простая быстрая сортировка
        QuickSort1<Integer> qs1=new QuickSort1<>();
        qs1.sort(a5,0,a5.length-1);
        qs1.showStat();
        qs1.clearStat();

        //Быстрая сортировка с выбором медианного опорного значения, комбинированная с сортировкой вставками
        QuickSort2<Integer> qs2=new QuickSort2<>();
        qs2.sort(a6, 0, a6.length-1);
        qs2.showStat();
        qs2.clearStat();

        //Сортировка массива, осортированного в обратном порядке
        Integer[] t0=new Integer[SIZE_ARRAY*5];
        Integer[] t1=new Integer[SIZE_ARRAY*5];
        Integer[] t2=new Integer[SIZE_ARRAY*5];
        for (int i=0;i<(SIZE_ARRAY*5);i++)t0[i]=(SIZE_ARRAY*5)-i-1;
        t1=Arrays.copyOf(t0,SIZE_ARRAY*5);
        t2=Arrays.copyOf(t0,SIZE_ARRAY*5);

        System.out.println();
        System.out.println("Сравнение различных методов быстрой сортировки при сортировке массива размера "+(SIZE_ARRAY*5)+", элементы которого уже упорядочены в обратном порядке");

        qs1.sort(t1,0,t1.length-1);
        qs1.showStat();

        qs2.sort(t2,0,t2.length-1);
        qs2.showStat();

    }

}
