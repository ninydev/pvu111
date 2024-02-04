package org.example.works.feb04.crypto;

public class MyWallet {
    String address = "3d2438ec33773a0864bcce0f6d7e1ef1af351dfdb7d48fd7edb4093cc49c23bc";
    double count =  0;
    double minCount = 1;

    /**
     * Операция покупки 1 криптомонеты кем то - я списываю со своего счета 1
     * @return
     */
    public synchronized double doSellerOne() {
        while (count < minCount) {
            try {
                wait();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        this.count--;
        return count;
    }

    /**
     * Пополнение моего счета на нужное мне количество монет
     * @param count
     */
    public synchronized void doBue(double count) {
        this.count += count;
        while (count < minCount) {
            try {
                wait();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        notifyAll();
    }
}
