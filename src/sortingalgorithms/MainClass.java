package sortingalgorithms;

import java.util.Arrays;

public class MainClass {

    private static final int SIZE_ARRAY=100;

    //Сортировка пузырьком
    private static <E extends Comparable<E>> E[] sortBubble(E[] e){
        if (e == null) return null;

        int compareCount=0;        //Счетчик сравнений
        int assignCount=0;         //Счетчик присваиваний

        boolean bitSwap=false;     //Равен true, если в результате прохода по массиву была выполнена хоть одна перестановка
        E tmp;                     //Переменная для временного хранения перемещаемых элементов

        for (int i=(e.length-1);i>0;i--){
            bitSwap=false;
            for (int j=1;j<=i;j++){
                if (e[j-1].compareTo(e[j])==1){
                    compareCount++;
                    tmp=e[j-1];
                    e[j-1]=e[j];
                    e[j]=tmp;
                    assignCount+=3;
                    bitSwap=true;
                    continue;
                }
                compareCount++;
            }
            if (!bitSwap)break;
        }

        //Выводим статистику по работе алгоритма
        System.out.println();
        System.out.println("Сортировка пузырьком");
        System.out.println("    выполнено сравнений: "+compareCount);
        System.out.println("    выполнено присваиваний: "+assignCount);

        return e;
    }

    //Сортировка вставками
    private static <E extends Comparable<E>> E[] sortInsert(E[] e) {
        if (e == null) return null;

        int compareCount=0;        //Счетчик сравнений
        int assignCount=0;         //Счетчик присваиваний

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
        System.out.println("    выполнено сравнений: "+compareCount);
        System.out.println("    выполнено присваиваний: "+assignCount);

        return e;
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
        Integer[] a0=new Integer[SIZE_ARRAY];   //Исходный массив
        Integer[] a1=new Integer[SIZE_ARRAY];   //Массив для сортировки пузырьком
        Integer[] a2=new Integer[SIZE_ARRAY];   //Массив для сортировки выбором
        Integer[] a3=new Integer[SIZE_ARRAY];   //Массив для сортировки вставками

        //Заполняем и выводим исходный массив
        for (int i=0;i<SIZE_ARRAY;i++)a0[i]=new Integer((int) (Math.random()*SIZE_ARRAY));
        System.out.println("Исходный числовой массив (целые числа):");
        show(a0);

        //Заполняем вспомогательные массивы, которые будем потом сортировать
        a1=Arrays.copyOf(a0,SIZE_ARRAY);
        a2=Arrays.copyOf(a0,SIZE_ARRAY);
        a3=Arrays.copyOf(a0,SIZE_ARRAY);

        //Сортировка пузырьком
        a1=sortBubble(a1);

        //Сортировка вставками
        a3= sortInsert(a3);

    }

}
