package org.example.works.marth03.my_threads;

import lombok.Data;

import java.util.ArrayList;


/**
 * Данные, которые я собираюсь каким то образом блокировать
 * Я оберну в класс прокладку - Resource
 */
@Data
public class ArrayResource
{
    // Флаг, который сообщает - готов ли ресурс к дальнейшей работе
    private boolean isFill = false;
    private ArrayList<Integer> data = new ArrayList<>();
}
