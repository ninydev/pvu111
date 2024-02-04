package org.example.works.feb04.crypto;

import java.util.ArrayList;
import java.util.List;

public class CryptoShop implements Runnable
{
    @Override
    public void run() {

        List<Thread> sellers = new ArrayList<>();

        MyWallet wallet = new MyWallet();
        CryptoBuyer b = new CryptoBuyer(wallet);


        for (int i = 0; i < 10; i++) {
            CryptoSeller seller = new CryptoSeller(wallet);
            sellers.add(seller);
            seller.start();
        }

        b.start();

    }
}
