package org.example.works.jan21;

import org.example.entities.Group;
import org.example.entities.User;
import org.example.exceptions.PasswordLengthException;
import org.example.exceptions.PasswordRegexException;

import java.util.ArrayList;

public class EntitiesTypes implements Runnable
{
    private void byGroups() {
        Group pvu111 = new Group("PVU", new ArrayList<User>());
        try {
            User nastya = new User("Nastya", "test@email.com", "Qwerty123");
            // Настя является членом группы
            nastya.setGroup(pvu111);

            // Группа содержит студента Настя
            pvu111.getUsers().add(nastya);

            // System.out.println(pvu111.getUsers().get(0).getGroup().getName());

            // Это Nastya
            System.out.println(pvu111.getUsers().get(0).getName());
            nastya.setName("Настя");
        } catch (PasswordLengthException passwordLengthException) {
            System.out.println(" ПРоблемма с длиной");
            System.out.println(passwordLengthException.getMessage());
        } catch (PasswordRegexException passwordRegexException) {
            System.out.println(" Нет символов или цифр - пароль нужно сделать сложнее");
            System.out.println(passwordRegexException.getMessage());
        } catch (Exception exception) {
            System.out.println(" Прогаммист был ленивый - по этому все остальные проблемы обработал тут");
            System.out.println(exception.getMessage());
        }
        finally {
            // Код финализации
        }

        // Это Настя
        System.out.println(pvu111.getUsers().get(0).getName());

    }

    @Override
    public void run() {
        byGroups();
    }

    private void byEntities() {
        // User user = new User("Oleksandr Nykytin");
//        User user = new User("Oleksandr Nykytin", "keeper@ninyev.com", "QweAsdZxc!23");
//        user.setAge(47);
//        System.out.println(user);
    }
}
