package bank;

public class AccountNumberGenerator {
    //статичную приватную переменную типа ри, с начальным значением 0;
    private static int counter = 0;

    // открытый статичный метод, который увеличивает значение статичной переменной на 1
    public static int getNext() {
        return ++counter;
    }

    // открытый статичный метод, который возвращает значение статичной переменной;
    public static int getCurrent(){
        return counter;
    }

    //открытый метод, устанавливающий значение переменной в 0;
    public static void reset(){
        counter = 0;
    }

    //Этот класс нужно использовать для генерации номеров счетов.
    public static String formatInteger(int number){
        return String.format("%06d", number);
    }
}
