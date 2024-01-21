package org.example.works.jan21;

import org.example.entities.User;

public class EntitiesTypes implements Runnable
{

    @Override
    public void run() {
        // User user = new User("Oleksandr Nykytin");
        User user = new User("Oleksandr Nykytin", "keeper@ninyev.com", "QweAsdZxc!23");
        user.setAge(47);
        System.out.println(user);
    }
}
