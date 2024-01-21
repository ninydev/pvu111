package org.example.works.jan21;

import org.example.entities.User;

public class EntitiesTypes implements Runnable
{

    @Override
    public void run() {
        User user = new User();
        System.out.println(user);
    }
}
