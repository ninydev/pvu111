package org.example.entities.baggage;

public class LittleDog extends AbstractBaggage implements BaggageInterface {
    @Override
    public int getSize() {
        return 0;
    }

    public void eat(){
        System.out.println(" Eat ");
    }
}
