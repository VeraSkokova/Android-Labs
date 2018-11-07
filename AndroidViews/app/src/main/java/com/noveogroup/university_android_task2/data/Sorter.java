package com.noveogroup.university_android_task2.data;

import com.noveogroup.university_android_task2.data.model.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Sorter {
    private Sorter() {
    }

    public static List<Person> sort(List<Person> personList, boolean ascendingSort, SortingType sortingType) {
        if (sortingType.equals(SortingType.AGE)) {
            return sortByAge(personList, ascendingSort);
        } else {
            return sortByGender(personList, ascendingSort);
        }
    }

    private static List<Person> sortByAge(List<Person> personList, boolean ascendingSort) {
        if (ascendingSort) {
            Collections.sort(personList, new AscendingAgeComparator());
        } else {
            Collections.sort(personList, new DescendingAgeComparator());
        }

        return personList;
    }

    private static List<Person> sortByGender(List<Person> personList, boolean ascendingSort) {
        if (ascendingSort) {
            Collections.sort(personList, new AscendingGenderComparator());
        } else {
            Collections.sort(personList, new DescendingGenderComparator());
        }

        return personList;
    }

    private static int sortByName(Person first, Person second) {
        return first.getName().compareTo(second.getName());
    }

    private static int getComparison(int result, Person first, Person second) {
        return result != 0 ? result : sortByName(first, second);
    }

    public enum SortingType {
        AGE,
        GENDER
    }

    private static class AscendingAgeComparator implements Comparator<Person> {
        @Override
        public int compare(Person first, Person second) {
            int result = Integer.valueOf(first.getAge()).compareTo(second.getAge());
            return getComparison(result, first, second);
        }
    }

    private static class DescendingAgeComparator implements Comparator<Person> {
        @Override
        public int compare(Person first, Person second) {
            int result = Integer.valueOf(second.getAge()).compareTo(first.getAge());
            return getComparison(result, first, second);
        }
    }

    private static class AscendingGenderComparator implements Comparator<Person> {
        @Override
        public int compare(Person first, Person second) {
            int result = first.getGender().compareTo(second.getGender());
            return getComparison(result, first, second);
        }
    }

    private static class DescendingGenderComparator implements Comparator<Person> {
        @Override
        public int compare(Person first, Person second) {
            int result = second.getGender().compareTo(first.getGender());
            return getComparison(result, first, second);
        }
    }
}
