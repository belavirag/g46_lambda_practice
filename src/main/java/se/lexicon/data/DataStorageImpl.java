package se.lexicon.data;


import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Create implementations for all methods. I have already provided an implementation for the first method *
 */
public class DataStorageImpl implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl() {
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        return personList.stream().filter(filter).toList();
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
        return personList.stream().filter(filter).findFirst().orElse(null);
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString) {
        Person p = this.findOne(filter);
        return p == null ? null : personToString.apply(p);
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString) {
        return findMany(filter).stream().map(personToString).toList();
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer) {
        findMany(filter).forEach(consumer);
    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator) {
        return personList.stream().sorted(comparator).toList();
    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator) {
        return findMany(filter).stream().sorted(comparator).toList();
    }
}
