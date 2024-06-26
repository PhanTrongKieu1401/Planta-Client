package vn.edu.ptit.planta.data;

import androidx.annotation.NonNull;


import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import vn.edu.ptit.planta.data.service.MyPlantService;
import vn.edu.ptit.planta.data.service.MyScheduleService;
import vn.edu.ptit.planta.data.service.PlantService;
import vn.edu.ptit.planta.data.service.UserService;


public class RetrofitClient {

    private static final String BASE_URL = "https://adapted-ox-suitably.ngrok-free.app/api/";;

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @NonNull
    public static PlantService getPlantService() {
        return retrofit.create(PlantService.class);
    }
    @NonNull
    public static MyPlantService getMyPlantService() {
        return retrofit.create(MyPlantService.class);
    }

    @NonNull
    public static MyScheduleService getMyScheduleService() { return retrofit.create(MyScheduleService.class); }
    @NonNull
    public static UserService getUserService() {
        return retrofit.create(UserService.class);
    }
}

