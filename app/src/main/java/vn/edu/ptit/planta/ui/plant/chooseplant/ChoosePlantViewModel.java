package vn.edu.ptit.planta.ui.plant.chooseplant;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.ptit.planta.data.RetrofitClient;
import vn.edu.ptit.planta.model.ApiResponse;
import vn.edu.ptit.planta.model.plant.Plant;

public class ChoosePlantViewModel extends ViewModel {

    private ChoosePlantNavigator choosePlantNavigator;
    private MutableLiveData<List<Plant>> listPlant;
    private List<Plant> plants;
    private MutableLiveData<Boolean> isSearch;
    private MutableLiveData<String> textSearch;
    private String key;
    private MutableLiveData<Boolean> isLoad;


    public ChoosePlantViewModel() {
        listPlant = new MutableLiveData<>();
        initData();
    }

    public void setChoosePlantNavigator(ChoosePlantNavigator choosePlantNavigator) {
        this.choosePlantNavigator = choosePlantNavigator;
    }

    public void setKeySearch(String newKey){
        this.key = newKey;
        initDataSearch();
    }

    public MutableLiveData<Boolean> getIsLoad() {
        if(isLoad == null) isLoad = new MutableLiveData<>();
        return isLoad;
    }

    public MutableLiveData<Boolean> getIsSearch() {
        if(isSearch == null) isSearch = new MutableLiveData<>();
        return isSearch;
    }

    public MutableLiveData<String> getTextSearch() {
        if(textSearch == null) textSearch = new MutableLiveData<>();
        return textSearch;
    }

    private void initData() {
        plants = new ArrayList<>();
        getIsLoad().setValue(true);
//        isLoad.setValue(true);
        RetrofitClient.getPlantService().listPlants().enqueue(new Callback<ApiResponse<List<Plant>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Plant>>> call, Response<ApiResponse<List<Plant>>> response) {
                ApiResponse<List<Plant>> apiResponse = response.body();
                if (response.isSuccessful()){
                    if(apiResponse.isSuccess()){
                        plants = apiResponse.getResult();
                        listPlant.setValue(plants);
                        if(plants.size() == 0){
                            isLoad.setValue(false);
                        } else {
                            isLoad.setValue(false);
                        }
                    } else {
                        isLoad.setValue(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Plant>>> call, Throwable throwable) {

            }
        });
    }

    private void initDataSearch() {
        plants = new ArrayList<>();
        RetrofitClient.getPlantService().getPlantByName(key).enqueue(new Callback<ApiResponse<List<Plant>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Plant>>> call, Response<ApiResponse<List<Plant>>> response) {
                ApiResponse<List<Plant>> apiResponse = response.body();
                if (response.isSuccessful()){
                    if(apiResponse.isSuccess()){
                        plants = apiResponse.getResult();
                        listPlant.setValue(plants);
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Plant>>> call, Throwable throwable) {

            }
        });
    }

    public MutableLiveData<List<Plant>> getListPlant() {
        return listPlant;
    }

    public void onBlackClick() {
        choosePlantNavigator.handleBack();
    }
}
