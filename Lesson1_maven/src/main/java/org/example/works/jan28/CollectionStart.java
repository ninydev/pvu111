package org.example.works.jan28;

import org.example.entities.baggage.*;

import java.util.ArrayList;

public class CollectionStart implements Runnable
{
    @Override
    public void run() {

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
