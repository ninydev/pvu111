package org.example.works.jan28;

import org.example.entities.users.User;
import org.example.exceptions.PasswordLengthException;
import org.example.exceptions.PasswordRegexException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi implements Runnable{
    @Override
    public void run() {
        try {
            streamMethods();
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void streamMethods() throws PasswordLengthException, PasswordRegexException {
        ArrayList<User> people = new ArrayList<>();
        people.add(new User("Sasha", "keeper@ninydev.com", "QweAsdZxc!23"));
        people.add(new User("Katya", "keeper1@ninydev.com", "QweAsdZxc!23"));
        people.add(new User("Masha", "keeper1@ninydev.com", "Qwe23"));
        people.add(new User("I", "keeper1@ninydev.com", "Qwe23"));
        people.add(new User("N", "keeper1@ninydev.com", "IQweAsdZxcQweAsdZxc"));

        Stream<User> stream = people.stream();

        // Промежуточный
        // Формирует НОВЫЙ ПОТОК в котором содержаться те элементы, которые удовлетворяют условию
        // stream.filter(u-> u.getPassword().length() < 12);

        // Терминирующий - после его вызова поток закрывается
        // Применяет метод к каждому элементу в потоке
        // stream.forEach(u -> System.out.println(u.getName()));

        // Промежуточный
        // Формирует из сущности в  потоке новый поток с новыми сущностями
        // Stream<String> names = stream.map(u-> u.getName());

        // Промежуточный
        // Содает новый поток, когда нужно вренуть из 1 сущности несколько
//        System.out.println("flatMap: ");
//        Stream<String> flat = stream.flatMap(u -> Stream.of(
//                u.getName(), //0, 2, 4 ...
//                u.getPassword() // 1,3,5, ...
//        ));
//
//        flat.forEach(s-> System.out.println(s));

        // Промежуточный
        // Сортирует - если передан способ сортировки по нему - или по умолчанию
//        stream.sorted();
//        stream.sorted(Comparator.comparingInt(u -> u.getPassword().length()));

        // Промежуточный
        // Пропускают от начала - и устанавливают предел
        stream.skip(1);
        stream.limit(1);



        // stream.filter(u-> u.getPassword().length() < 12);

    }


    private void asMap() throws PasswordLengthException, PasswordRegexException {
        ArrayList<User> people = new ArrayList<>();
        people.add(new User("Sasha", "keeper@ninydev.com", "QweAsdZxc!23"));
        people.add(new User("Katya", "keeper1@ninydev.com", "QweAsdZxc!23"));
        people.add(new User("Masha", "keeper1@ninydev.com", "Qwe23"));
        people.add(new User("I", "keeper1@ninydev.com", "Qwe23"));
        people.add(new User("N", "keeper1@ninydev.com", "IQweAsdZxcQweAsdZxc"));

        people.stream().map(p-> "Name:" + p.getName()).forEach(System.out::println);

        // Сначала я отберу тех, у кого длина имени меньше 2
        Stream<User> userStreamBadName = people.stream().filter(u -> u.getName().length() < 2);
        // Потом у тех кто подошел - я выберу тех, кто по паролю не прошел
        Stream<User> userStreamBadPassword = userStreamBadName.filter(u-> u.getPassword().length() < 12);

        List<User> result = userStreamBadPassword.collect(Collectors.toList());

        System.out.println("Result: ");
        for (User a : result) {
            System.out.println(a.getName());
        }


//        System.out.println(" Filter ");
//        people.stream()
//                .filter(p -> p.getPassword().length() < 12)
//                .forEach(System.out::println);

//        List<User> badPasswordCollection = people.stream()
//                .filter(p -> p.getPassword().length() < 12)
//                .collect(Collectors.toList());
//
//        System.out.println(" Collection from Stream ");
//        for (User u : badPasswordCollection) {
//            System.out.println(u.getName());
//        }

    }


    private void asStream() {
        TreeSet<String> peopleSet = new TreeSet<>();
        peopleSet.add("Vasya");
        peopleSet.add("Petya");
        peopleSet.add("Anna");
        peopleSet.add("Galya");

        // - тут я прохожу по всем элементам коллекции
        System.out.println("For: ");
        for (String p: peopleSet) {
            if(p.length()<5)
                System.out.println(p);
        }

        // Сформировать поток (ленту элементов) на основе коллекции
        Stream<String> stream;

//        stream= peopleSet.stream();

//        System.out.println("Stream: ");
//        stream
//                // Промежуточная операция
//                .filter(p-> p.length() < 5)
//                // Терминальная операция
//                .forEach( p-> System.out.println(p));
//
//        // Понадобилось открыть поток еще раз - в прошлой операции я его закрыл
//        stream = peopleSet.stream();
//        long c = stream.filter(p-> p.length() < 5).count();
//        System.out.println(c);

        Boolean nextStep = false;
        stream = peopleSet.stream();
        stream = stream.filter(p-> p.length() < 5);
        if (nextStep){
            stream = stream.filter(p-> p.length() > 2);
        }
        stream.forEach(System.out::println);

    }
}
