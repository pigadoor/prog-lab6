package com.pigadoor.application;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.MeleeWeapon;
import com.pigadoor.data.SpaceMarine;

public class InputManager {

    public String receiveName() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a name: ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("This value cannot be empty. Try again");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public Double receiveX() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter X coordinate: ");
                return scanner.nextDouble();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be double. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public Float receiveY() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter Y coordinate: ");
                Float y = scanner.nextFloat();
                if (y > 589) {
                    System.out.println("Max value is 589. Try again.");
                    continue;
                }
                return y;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be float. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public Coordinates receiveCoordinates() {
        return new Coordinates(receiveX(), receiveY());
    }

    public int receiveHealth() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter health: ");
                int health = scanner.nextInt();
                if (health <= 0) {
                    System.out.println("Value should be positive. Try again.");
                    continue;
                }
                return health;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be int. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public Long receiveHeartCount() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter heart count coordinate: ");
                Long heartCount = scanner.nextLong();
                if (heartCount <= 0 || heartCount > 3) {
                    System.out.println("Value should be from (0; 3] Try again.");
                    continue;
                }
                return heartCount;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be long. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public Float receiveHeight() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter height: ");
                return scanner.nextFloat();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be float. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public MeleeWeapon receiveMeleeWeapon() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Variants:\n     CHAIN_SWORD,\n" +
                        "    POWER_SWORD,\n" +
                        "    MANREAPER,\n" +
                        "    LIGHTING_CLAW,\n" +
                        "    POWER_FIST;\n");
                System.out.print("Enter melee weapon: ");
                String enter = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
                if (enter.equals("")) {
                    System.out.println("This value cannot be empty. Try again");
                    continue;
                }
                switch (enter) {
                    case "CHAIN_SWORD" -> {
                        return MeleeWeapon.CHAIN_SWORD;
                    }
                    case "POWER_SWORD" -> {
                        return MeleeWeapon.POWER_SWORD;
                    }
                    case "MANREAPER" -> {
                        return MeleeWeapon.MANREAPER;
                    }
                    case "LIGHTING_CLAW" -> {
                        return MeleeWeapon.LIGHTING_CLAW;
                    }
                    case "POWER_FIST" -> {
                        return MeleeWeapon.POWER_FIST;
                    }
                    default -> System.out.println("Incorrect enter. Value should be a string from list. Try again.");
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("Incorrect enter. Value should be a string from list. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public String receiveChapterName() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a chapter name: ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("This value cannot be empty. Try again");
                    continue;
                }
                return name;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public String receiveParentLegion() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter a parent legion: ");
                return scanner.nextLine().trim();
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("This value must be string. Try again.");
            } catch (NoSuchElementException noSuchElementException) {
                System.out.println("Program was stopped successfully.");
                System.exit(0);
            }
        }
    }

    public Chapter receiveChapter() {
        return new Chapter(receiveChapterName(), receiveParentLegion());
    }

    public SpaceMarine receiveSpaceMarine() {
        return new SpaceMarine(0, receiveName(), receiveCoordinates(), LocalDateTime.now(), receiveHealth(),
                receiveHeartCount(), receiveHeight(), receiveMeleeWeapon(), receiveChapter());
    }

}
