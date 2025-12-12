package hw12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


interface Visitor {
    void visit(HumanitarianStudent student);
    void visit(NaturalStudent student);
    void visit(MixedStudent student);
}

abstract class Student {
    protected int credits = 0;
    protected int money;
    protected int diplomaGoal;
    protected boolean isExpelled = false;

    public Student(int diplomaGoal, int startMoney) {
        this.diplomaGoal = diplomaGoal;
        this.money = startMoney;
    }

    public abstract void accept(Visitor visitor);

    public void addCredits(int amount) {
        if (!isExpelled) this.credits += amount;
    }

    public void addMoney(int amount) {
        if (!isExpelled) this.money += amount;
    }

    public void spendMoney(int amount) {
        if (isExpelled) return;
        if (this.money >= amount) {
            this.money -= amount;
        } else {
            this.isExpelled = true; // Нема грошей - відрахування
            System.out.println("Студента відраховано: не вистачило коштів.");
        }
    }

    public boolean hasDiploma() {
        return !isExpelled && credits >= diplomaGoal;
    }

    public void printStatus() {
        System.out.println("Кредити: " + credits + "/" + diplomaGoal + ", Гроші: " + money + ", Відрахований: " + isExpelled);
    }
}


class HumanitarianStudent extends Student {
    public HumanitarianStudent(int goal, int money) { super(goal, money); }
    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
}

class NaturalStudent extends Student {
    public NaturalStudent(int goal, int money) { super(goal, money); }
    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
}

class MixedStudent extends Student { // Природничо-гуманітарний
    public MixedStudent(int goal, int money) { super(goal, money); }
    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
}


class TeacherVisitor implements Visitor {
    private String type; // "humanitarian" або "natural"
    private int creditsToAdd;

    public TeacherVisitor(String type, int credits) {
        this.type = type;
        this.creditsToAdd = credits;
    }

    @Override
    public void visit(HumanitarianStudent s) {
        if (type.equals("humanitarian")) {
            s.addCredits(creditsToAdd);
        }
        // Якщо natural - несумісність
    }

    @Override
    public void visit(NaturalStudent s) {
        if (type.equals("natural")) {
            s.addCredits(creditsToAdd);
        }
        // Якщо humanitarian - нічого не робимо
    }

    @Override
    public void visit(MixedStudent s) {
        s.addCredits(creditsToAdd);
    }
}

class IncomeVisitor implements Visitor {
    private int amount;

    public IncomeVisitor(int amount) { this.amount = amount; }

    @Override public void visit(HumanitarianStudent s) { s.addMoney(amount); }
    @Override public void visit(NaturalStudent s) { s.addMoney(amount); }
    @Override public void visit(MixedStudent s) { s.addMoney(amount); }
}

class ExpenseVisitor implements Visitor {
    private int amount;

    public ExpenseVisitor(int amount) { this.amount = amount; }

    @Override public void visit(HumanitarianStudent s) { s.spendMoney(amount); }
    @Override public void visit(NaturalStudent s) { s.spendMoney(amount); }
    @Override public void visit(MixedStudent s) { s.spendMoney(amount); }
}


public class B12_01_StudentSimulation {
    public static void main(String[] args) {
        File folder = new File("src/hw12/task1");

        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Помилка: Папку 'task1' не знайдено!");
            return;
        }

        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            Arrays.sort(listOfFiles, Comparator.comparing(File::getName));

            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().startsWith("input")) {
                    simulateFile(file);
                    System.out.println("\n" + "=".repeat(40) + "\n"); // Розділювач між студентами
                }
            }
        } else {
            System.out.println("Папка пуста.");
        }
    }

    public static void simulateFile(File file) {
        System.out.println(">>> Обробка файлу: " + file.getName());

        try (Scanner scanner = new Scanner(file)) {

            if (!scanner.hasNext()) {
                System.out.println("Файл пустий.");
                return;
            }

            String typeLine = scanner.nextLine().trim();
            // trim() - з зайвими пробілами в файлах працює

            int goal = 0;
            int money = 0;

            try {
                goal = Integer.parseInt(scanner.nextLine().trim());
                money = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Помилка формату даних у файлі (некоректні числа).");
                return;
            }

            Student student = null;
            String typeLower = typeLine.toLowerCase();

            if (typeLower.contains("humanitarian") && typeLower.contains("natural")) {
                student = new MixedStudent(goal, money);
            } else if (typeLower.contains("humanitarian")) {
                student = new HumanitarianStudent(goal, money);
            } else if (typeLower.contains("natural")) {
                student = new NaturalStudent(goal, money);
            } else {
                System.out.println("Невідомий тип студента: " + typeLine);
                return;
            }

            System.out.println("Студент: " + typeLine + " | Ціль: " + goal + " | Гроші: " + money);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length == 0) continue;

                String command = parts[0];
                Visitor visitor = null;

                try {
                    if (command.equals("teach")) {
                        String teacherType = parts[1];
                        int credits;
                        if (parts.length > 2) {
                            credits = Integer.parseInt(parts[2]);
                        } else {
                            credits = (teacherType.equals("humanitarian")) ? 3 : 5;
                        }
                        visitor = new TeacherVisitor(teacherType, credits);

                    } else if (command.equals("pay")) {
                        int amount = Integer.parseInt(parts[2]);
                        visitor = new ExpenseVisitor(amount);

                    } else if (command.equals("obtain") || command.equals("get")) {
                        int amount = Integer.parseInt(parts[parts.length - 1]);
                        visitor = new IncomeVisitor(amount);
                    }
                } catch (Exception e) {
                    System.out.println("Помилка в рядку команди: " + line + " -> " + e.getMessage());
                    continue;
                }

                if (visitor != null) {
                    student.accept(visitor);
                }

                if (student.isExpelled) {
                    System.out.println("--- Студента було відраховано під час семестру ---");
                    break;
                }
            }

            // Фінальний результат
            System.out.print("Результат: ");
            student.printStatus();
            if (student.hasDiploma()) {
                System.out.println("[+] ДИПЛОМ ОТРИМАНО");
            } else {
                System.out.println("[-] ДИПЛОМ НЕ ОТРИМАНО");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не доступний.");
        }
    }
}