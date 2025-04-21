package work;

public class Main {
    public static void main(String[] args) {
        /*
        Создание нейромодуля; 2500; INCOME; 25.03.2036
        Оплата телепорта; 400.00; EXPENSE; 24.03.2036
        Билет на Марс; 2599.99; EXPENSE; 25.03.2036
        Премия; 10000; INCOME; 25.03.2036
        Скин на цифрового аватара; 3900; EXPENSE; 26.03.2036
        Доставка наноеды; 745.89; EXPENSE; 27.03.2036
        */

        UserMenu userMenu = new UserMenu();
        userMenu.inFromUser();

        userMenu.getReport();

    }
}
