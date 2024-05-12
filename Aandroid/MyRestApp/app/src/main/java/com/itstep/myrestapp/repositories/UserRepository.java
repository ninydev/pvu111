package com.itstep.myrestapp.repositories;

import android.util.Log;
import android.widget.ImageView;

import com.itstep.myrestapp.clients.MockApiClient;
import com.itstep.myrestapp.models.UserModel;
import com.itstep.myrestapp.services.UserApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository implements RepositoryInterface<UserModel>
{


    ArrayList<UserModel> data;

    UserApiService apiService;





    private static UserRepository instance;

    private UserRepository(){
        data = new ArrayList<>();
        apiService = MockApiClient.getInstance().create(UserApiService.class);
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void getAll() {
        Call<ArrayList<UserModel>> call = apiService.getAll();
        call.enqueue(new Callback<ArrayList<UserModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserModel>> call, Response<ArrayList<UserModel>> response) {
                if (response.isSuccessful()) {
                    ArrayList<UserModel> userModels =  new ArrayList<>();
                    userModels.addAll(response.body());
                    for (int i = 0; i < userModels.size(); i++) {
                        Log.d("keeper", userModels.get(i).getAvatar());
                    }
                } else {
                    // Обработка ошибки
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserModel>> call, Throwable t) {
                // Обработка ошибки
            }
        });
    }


    @Override
    public UserModel create(UserModel newModel) {
        // 1 min
        data.add(newModel);


        // Вызываем метод создания пользователя
        Call<UserModel> call = apiService.create(newModel);

        // Асинхронный вызов, добавляем обработчик для ответа и ошибок
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.isSuccessful()) {
                    // Запрос успешно выполнен, обрабатываем ответ
                    UserModel createdUser = response.body();
                    System.out.println("Пользователь успешно создан: " + createdUser);
                } else {
                    // Обработка ошибки
                    System.out.println("Ошибка при создании пользователя: " + response.message());
                    // Здесь вы можете выполнить дополнительные действия в зависимости от ошибки
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                // Ошибка при выполнении запроса
                System.out.println("Ошибка при отправке запроса: " + t.getMessage());
                // Здесь вы можете выполнить дополнительные действия при ошибке сети или других проблемах
            }
        });

        return newModel;
    }


    @Override
    public UserModel createModel() {
        UserModel newUser = new UserModel();
        newUser.setId(22);
        return newUser;
    }
}
