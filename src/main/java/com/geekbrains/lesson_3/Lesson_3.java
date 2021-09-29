package com.geekbrains.lesson_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Lesson_3 {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        chooseGame(); // игровое меню
    }

    /**
     * Игровое меню
     *
     * @throws Exception - ловим некорректный воод с клавиатуры
     */
    public static void chooseGame() throws Exception {
        System.out.println("\u001B[40m +++ МЕНЮ +++ \u001B[0m");
        System.out.println("1 - \"Угадай число\"");
        System.out.println("2 - \"Угадай слово\"");
        System.out.println("3 - ВЫХОД");
        System.out.print("Твой выбор - ");
        int choose = 0; // выбранный пользователем пункт меню
        while (choose < 1 || choose > 3) { //повторяем, пока польззователь не сделает корректный ввод
            try {
                choose = Integer.parseInt(reader.readLine());
                switch (choose) {
                    case (1):
                        gameCatchRandomNumber(); // выбрать игру "Угадай число"
                        break;
                    case (2):
                        gameCatchRandomWord(); // вырать игру "Угадай слово"
                        break;
                    case (3):
                        System.out.println("\u001B[47m\u001B[30m== ВЫХОД ==\u001B[0m"); // Завершить программу
                        break;
                    default:
                        throw new Exception(); // если в меню сделан некорректный выбор - просим сделать выбор заново
                }
            } catch (Exception e) {
                System.out.print("Ошибка ввода. Повтори выбор - ");
            }
        }
    }

    /**
     * Игра "Угадай число"
     * Компьютер загадывает число от 0 до 9
     * Пользователь за 3 попытки должен угадать загаданное число
     *
     * @throws Exception - ловим некорректный воод с клавиатуры
     */
    public static void gameCatchRandomNumber() throws Exception {
        System.out.println("\u001B[42m\u001B[30m++++++++++++\u001B[0m");
        System.out.println("\u001B[42m\u001B[30mУГАДАЙ ЧИСЛО\u001B[0m");
        System.out.println("\u001B[42m\u001B[30m++++++++++++\u001B[0m");
        while (true) {
            boolean isWin = false; // признак победы пользователя (пользователь угадал загаданное число не более чем за 3 попытки)
            int counter = 0; // счетчик попыток угадать число
            int compNumber = (int) (Math.random() * 10); // компьютер загадывает число
            System.out.println("\u001B[43m\u001B[30m== Компьютер загадал число от 0 до 9 ==\u001B[0m");
            System.out.println("У тебя есть три попытки, чтобы угадать это число!");
            while (!isWin) {
                int userNumber = -1; // переменная для хода пользователя
                counter++;
                if (counter > 3) {
                    System.out.println("======================================================");
                    System.out.println("Загаданное число \"" + compNumber + "\" не угадано, все попытки потрачены");
                    System.out.println("======================================================");
                    break;
                }
                System.out.print("Введи число от 0 до 9: ");
                while (userNumber < 0 || userNumber > 9) { // пока не будет введено число от 0 до 9, просим повторить
                    try {
                        userNumber = Integer.parseInt(reader.readLine());
                        if (userNumber < 0 || userNumber > 9) {
                            System.out.print("Неправильный формат ввода. Введи число от 0 до 9: ");
                        }
                    } catch (Exception e) { // если введено не число, просим повторить
                        System.out.print("Неправильный формат ввода. Введи число от 0 до 9: ");
                    }
                }
                if (userNumber == compNumber) { // Если число угадано, игра завершается
                    System.out.println("========================");
                    System.out.printf("Ты угадал c %s-й попытки!\n", counter);
                    System.out.println("========================");
                    isWin = true;
                    break;
                } else if (userNumber < compNumber) {
                    System.out.println("Твое число меньше загаданного компьютером");
                } else {
                    System.out.println("Твое число больше загаданного компьютером");
                }
            }
            System.out.print("Попробовать еще раз? (ДА - 1, НЕТ - 0): ");
            int isWantRepeat = -1;
            while (isWantRepeat < 0 || isWantRepeat > 1) { // ввод значения, пока не введен 0 или 1
                try {
                    isWantRepeat = Integer.parseInt(reader.readLine()); // выбор еще одной попытки или завершения игры
                    if (isWantRepeat < 0 || isWantRepeat > 1)
                        System.out.print("Неправильный формат ввода. Введи 1 (ДА) или 0 (НЕТ): ");
                } catch (Exception e) {
                    System.out.print("Неправильный формат ввода. Введи 1 (ДА) или 0 (НЕТ): ");
                }
            }
            if (isWantRepeat == 0) { // если введен 0, то игра завершается
                System.out.println("\u001B[47m\u001B[30m== КОНЕЦ ИГРЫ ==\u001B[0m");
                break;
            }
        }
    }

    /**
     * Игра "Угадай слово"
     * Компьтер загадывает слово, пользователь пытается отгадать
     * Через каждые 3 неудачные попытки угадать слово компьютер дает подсказку
     * Игра продолжается, пока пользователь не угадает слово
     *
     * @throws Exception - ловим некорректный воод с клавиатуры
     */
    public static void gameCatchRandomWord() throws Exception {
        System.out.println("\u001B[42m\u001B[30m++++++++++++\u001B[0m");
        System.out.println("\u001B[42m\u001B[30mУГАДАЙ СЛОВО\u001B[0m");
        System.out.println("\u001B[42m\u001B[30m++++++++++++\u001B[0m");
        String[] words = {"яблоко", "апельсин", "лимон", "банан", "абрикос",
                "авокадо", "брокколи", "морковь", "вишня", "чеснок",
                "виноград", "дыня", "персик", "киви", "манго",
                "гриб", "орех", "слива", "горох", "арахис",
                "груша", "перец", "ананас", "тыква", "картофель"};
        while (true) {
            boolean isWin = false; // признак завершения игры (если слово угадано)
            int userTries = 0; // счетчик попыток угадать слово
            int promtForUser = 0; // счетчик количества подсказок
            String compWord = words[(int) (Math.random() * 26)]; // компьютер загадывает слово
            System.out.println("\u001B[43m\u001B[30m== Компьютер загадал слово (это что-то съедобное) ==\u001B[0m");
            while (!isWin) {
                userTries++;
                System.out.print("Попробуй угадать (введи здесь свой вариант): ");
                String userWord = reader.readLine();

                if (userWord.equals(compWord)) { // если слово угадано, игра завершается
                    System.out.println("=======");
                    System.out.println("Угадал!");
                    System.out.println("=======");
                    isWin = true;
                } else if ((userTries % 3 != 0)) { // если слово не угадано и не пора выдавать подсказку, компьютер показывает, есть ли в начале слов одинаковые буквы
                    int minNumberOfChars = (compWord.length() < userWord.length() ? compWord.length() : userWord.length()); // определение, какое слово короче, чтобы в цикле количество итераций не вышло за пределы длины самого короткого слова
                    int equalsCharsCount = 0; //
                    for (int i = 0; i < minNumberOfChars; i++) { // пока есть одинаковые буквы, они выводятся в консоль
                        if (compWord.charAt(i) == userWord.charAt(i)) {
                            System.out.print(compWord.charAt(i));
                            equalsCharsCount++;
                        } else { // как только буквы перестали совпадать, цикл проверки совпадений завершается
                            break;
                        }
                    }
                    for (int i = 0; i < (15 - equalsCharsCount); i++) { // после одинаковых букв строка заполняется символом '#' (до 15 символов)
                        System.out.print("#");
                    }
                    System.out.println();
                }

                if (userTries % 3 == 0 && isWin != true) { // после каждой 3-й неудачной попытки угадать компьютер дает подсказку
                    promtForUser++;
                    System.out.print("\u001B[44m\u001B[30m" + promtForUser + "\u001B[44m\u001B[30m-я подсказка: \u001B[0m");
                    for (int i = 0; i < promtForUser; i++) { // с каждой следующей подсказкой открываем на одну букву больше
                        System.out.print("\u001B[44m\u001B[30m" + compWord.charAt(i) + "\u001B[0m");
                    }
                    for (int i = 0; i < (15 - promtForUser); i++) { // заполняем строку символами '#' (до 15 символов)
                        System.out.print("\u001B[44m\u001B[30m#\u001B[0m");
                    }
                    System.out.println();
                }
            }
            System.out.print("Попробовать еще раз? (ДА - 1, НЕТ - 0): ");
            int isWantRepeat = -1;
            while (isWantRepeat < 0 || isWantRepeat > 1) { // ввод значения, пока не введен 0 или 1
                try {
                    isWantRepeat = Integer.parseInt(reader.readLine()); // выбор еще одной попытки или завершения игры
                    if (isWantRepeat < 0 || isWantRepeat > 1)
                        System.out.print("Неправильный формат ввода. Введи 1 (ДА) или 0 (НЕТ): ");
                } catch (Exception e) {
                    System.out.print("Неправильный формат ввода. Введи 1 (ДА) или 0 (НЕТ): ");
                }
            }
            if (isWantRepeat == 0) { // если введен 0, то игра завершается
                System.out.println("\u001B[47m\u001B[30m== КОНЕЦ ИГРЫ ==\u001B[0m");
                break;
            }
        }
    }
}


