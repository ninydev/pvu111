package org.example.entities.users;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Group {

    private String name;

    private ArrayList<User> users ;
}
