package org.example.works.jan21;

import org.example.entities.monitors.MonitorTypeInterface;
import org.example.entities.monitors.MonitorType;
import org.example.entities.monitors.PowerType;
import org.example.entities.monitors.PowerTypeInterface;
import org.example.entities.users.Group;
import org.example.entities.users.User;
import org.example.exceptions.PasswordLengthException;
import org.example.exceptions.PasswordRegexException;
import org.example.ui.button.ClickInterface;
import org.example.ui.button.HoverInterface;

import java.util.ArrayList;

public class EntitiesTypes implements Runnable
{
    @Override
    public void run() {
        byButton();
    }

    private void byButton() {
        ClickInterface button = new ClickInterface() {
            @Override
            public void click(Object event) {
                System.out.println("Click Alert");
            }
        };

        button.click(null);

        HoverInterface hoverButton = new HoverInterface() {
            @Override
            public void onMouseOver(Object event) {}

            @Override
            public void onMouseOut(Object event) {}
        };


    }

    private void byMonitor(){
        // Monitor samsung = new Monitor();
        // MonitorTypeInterface samsung = new Monitor();
        MonitorTypeInterface someTypeMonitor = new MonitorTypeInterface() {
            MonitorType type;

            @Override
            public MonitorType getInputType() {
                return this.type;
            }

            @Override
            public void setInputType(MonitorType type) {this.type = type;}
        };
        System.out.println(someTypeMonitor.getInputType());

        PowerTypeInterface somePowerMonitor = new PowerTypeInterface() {
            PowerType type;
            @Override
            public PowerType getPowerType() {
                return type;
            }

            @Override
            public void setPowerType(PowerType type) {
                this.type = type;
            }
        };
        System.out.println(somePowerMonitor.getPowerType());
        // System.out.println(someMonitor.getPowerType());
    }

    private void byGroups() {
        Group pvu111 = new Group("PVU", new ArrayList<User>());
        try {
            User nastya = User.createEntity("Nastya", "test@email.com", "Qwerty123");
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


    private void byEntities() {
        // User user = new User("Oleksandr Nykytin");
//        User user = new User("Oleksandr Nykytin", "keeper@ninyev.com", "QweAsdZxc!23");
//        user.setAge(47);
//        System.out.println(user);
    }
}
