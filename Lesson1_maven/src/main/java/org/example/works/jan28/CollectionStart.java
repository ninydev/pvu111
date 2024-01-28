package org.example.works.jan28;

import org.example.entities.baggage.*;

import java.util.*;

public class CollectionStart implements Runnable
{
    @Override
    public void run() {
        asHash();
    }

    private void asHash(){
        HashSet<Basket> baskets = new HashSet<>();
        Basket a = new Basket();
        Basket b = new Basket();

        baskets.add(a);
        baskets.add(b);

        // Этот объект не будет добавлен - он уже есть в коллекции
        baskets.add(a);

        for(Basket basket : baskets){
            System.out.println(basket);
        }
    }

    private void asMap() {
        HashMap<String, String> redis = new HashMap<>();

        redis.put("key", "value");

        // Таким образом осущетсвляется кеширование
        redis.put("allPost", "[{id:1, title='FirstPost'}, {id:2, title='SecondPost'}]");
        redis.put("postId_1", "{id:1, title='FirstPost'}");
        redis.put("cacheKey", "json form DB");
    }

    private void asTree(){
        TreeSet<String> peopleSet = new TreeSet<>();
        peopleSet.add("Vasya");
        peopleSet.add("Petya");
        peopleSet.add("Anna");
        peopleSet.add("Galya");

        // Реализация foreach в Java
        for (String p: peopleSet) {
            System.out.println(p);
        }

    }

    private void asList() {
        // List - interface
        // ArrayList - class
        List<BaggageInterface> arrList = new ArrayList<>();

        // Одновсязный список - реализация структуры данных Stack
        LinkedList<String> people = new LinkedList<>();
        people.push("vasya");
        people.push("petya");

        String p;
        // p=people.getFirst();
//        p=people.pop();
//        p= people.pop();

        ArrayDeque<String> peopleDeque = new ArrayDeque<>();

        peopleDeque.push("vasya");
        peopleDeque.push("petya");

        p= peopleDeque.getFirst();
        p= peopleDeque.getLast();

//        p= peopleDeque.pop();
//        p= peopleDeque.pop();

    }


    /**
     * Коллекция - как коробка - должна обладать набором методов работы с ней
     * - положить в коробку
     * - достать из коробки
     * - сколько в коробке элементов
     */
    private void simpleCollectionAsInterface() {
        Collection<BaggageInterface> simpleCollection = new Collection<BaggageInterface>() {

            private ArrayList<BaggageInterface> data = new ArrayList<>();

            // Сколько элементов в коробке
            @Override
            public int size() {
                return data.size();
                // return 0;
            }

            // Сообщать что коробка пуста
            @Override
            public boolean isEmpty() {
                return  data.isEmpty();
                // return false;
            }

            // Есть ли такой элемент в коллекции
            @Override
            public boolean contains(Object o) {
                return data.contains(o);
            }

            // Указалель на очередной элемент - нужен для перебора
            @Override
            public Iterator<BaggageInterface> iterator() {
                return data.iterator();
            }

            // Используется для экспорта коллекции в массив Object
            @Override
            public Object[] toArray() {
                return data.toArray();
            }

            // Для экспорта в определенный тип (если такое возможно
            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            // Положить в коллекцию
            @Override
            public boolean add(BaggageInterface element) {
                return data.add(element);
            }

            // Убрать из коллекции
            @Override
            public boolean remove(Object o) {
                return data.remove(o);
            }

            // Сравнить коллекции
            @Override
            public boolean containsAll(Collection<?> c) {
                return data.containsAll(c);
            }

            // Добавить элементы из другой коллекции
            @Override
            public boolean addAll(Collection<? extends BaggageInterface> c) {
                return data.addAll(c);
            }

            // Убрать элементы
            @Override
            public boolean removeAll(Collection<?> c) {
                return data.removeAll(c);
            }

            // Удалить лишние
            @Override
            public boolean retainAll(Collection<?> c) {
                return data.retainAll(c);
            }

            // Очистить коллекцию
            @Override
            public void clear() {
                data.clear();
            }

            // Срквить коробку
            @Override
            public boolean equals(Object o) {
                return false;
            }

            // Вернуть хеш класса коробки
            @Override
            public int hashCode() {
                return 0;
            }
        };
    }

    private void asInterface(){
        /**
         * Когда нужно выделить нечто важное в раках задачи
         */
        ArrayList<BaggageInterface> collection = new ArrayList();

        collection.add(new Painting());
        collection.add(new Basket());
        collection.add(new Cardboard());
        collection.add(new LittleDog());
        collection.add(new BaggageInterface() {
            @Override
            public int getSize() {
                return 0;
            }
        });

        for (int i = 0; i < collection.size(); i++) {
            System.out.println("Size: " + collection.get(i).getSize());

            try {
                ((LittleDog) collection.get(i)).eat();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            if(collection.get(i) instanceof LittleDog) {
                System.out.println("Element " + i + " is a dog");
                ((LittleDog) collection.get(i)).eat();
            }
        }

    }
    private void asChildren(){
        /**
         * Собирать коллекцию на основе наследников удобно, когда основные методы работы с сущностью
         * описаны в классе предке. Например - коллекция моделей (для работы с базой данных)
         */
        ArrayList<AbstractBaggage> collection = new ArrayList();

        collection.add(new Painting());
        collection.add(new Basket());
        collection.add(new Cardboard());
        collection.add(new LittleDog());
        // collection.add(new AbstractBaggage());

    }

    private void asObject(){
        ArrayList<Object> collection = new ArrayList();

        collection.add(new Painting());
        collection.add(new Basket());
        collection.add(new Cardboard());
        collection.add(new LittleDog());

    }
}
