package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.function.Function;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        storage.findMany(p -> p.getFirstName().equals("Erik")).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        storage.findMany(p -> p.getGender().equals(Gender.FEMALE)).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        final LocalDate date = LocalDate.of(2000, 1, 1);
        storage.findMany(p -> p.getBirthDate().isAfter(date) || p.getBirthDate().isEqual(date)).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        System.out.println(storage.findOne(p -> p.getId() == 123));

        System.out.println("----------------------");

    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        Function<Person, String> converter = p -> p.getFirstName() + " " + p.getLastName() + " born " + p.getBirthDate().toString();
        System.out.println(storage.findOneAndMapToString(p -> p.getId() == 456, converter));

        System.out.println("----------------------");
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        storage.findManyAndMapEachToString(p -> p.getFirstName().startsWith("E") && p.getGender().equals(Gender.MALE), Person::toString).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        Function<Person, String> converter = p -> {
            StringBuilder builder = new StringBuilder();
            builder.append(p.getFirstName()).append(" ").append(p.getLastName()).append(" ");
            builder.append(Period.between(p.getBirthDate(), LocalDate.now()).getYears());
            builder.append(" years");
            return builder.toString();
        };

        storage.findManyAndMapEachToString(p -> Period.between(p.getBirthDate(), LocalDate.now()).getYears() < 10, converter).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        storage.findAndDo(p -> p.getFirstName().equals("Ulf"), System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        storage.findAndDo(p -> p.getLastName().contains(p.getFirstName()), System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        storage.findAndDo(p -> {
            String firstNameLowered = p.getFirstName();
            String reversed = new StringBuilder(firstNameLowered).reverse().toString();
            return firstNameLowered.equals(reversed);

        }, p -> System.out.println(p.getFirstName() + " " + p.getLastName()));

        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        storage.findAndSort(p -> p.getFirstName().startsWith("A"), Comparator.comparing(Person::getBirthDate)).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        final LocalDate date = LocalDate.of(1950, 1, 1);
        storage.findAndSort(p -> p.getBirthDate().isBefore(date), Comparator.comparing(Person::getBirthDate)).forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        storage.findAndSort(Comparator.comparing(Person::getLastName)
                    .thenComparing(Person::getFirstName)
                    .thenComparing(Person::getBirthDate)
                ).forEach(System.out::println);

        System.out.println("----------------------");
    }
}
