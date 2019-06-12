package com.codefly.weather.views.fragments;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.codefly.weather.R;
import com.codefly.weather.models.Forecastday;
import com.codefly.weather.models.Weather;
import com.codefly.weather.repository.Resource;
import com.codefly.weather.viewmodels.WeatherViewModel;
import com.codefly.weather.viewmodels.factory.ViewModelFactory;
import com.codefly.weather.views.adapter.ForecastAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import dagger.android.support.AndroidSupportInjection;
import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by YATRAONLINE\sohan.gupta on 25/5/19.
 */

public class WeatherFragment extends Fragment implements View.OnClickListener{
    private static final int REQUEST_CODE_NETWORK_PROVIDER=101;
    private static final int REQUEST_CODE_LOCATION_PERMISSION=102;
    @Inject
    ViewModelFactory mViewModelFactory;
    private LocationManager mLocationManager;
    private WeatherViewModel mViewModel;
    private LinearLayout mWeatherLay;
    private LinearLayout mErrorLay;
    private ProgressBar mProgressView;
    private TextView mCityTextView;
    private TextView mTempTextView;
    private RecyclerView mRecyclerView;
    private Button mButtonRetry;
    private ForecastAdapter mAdapter;
    private WeatherViewModel.GeocoderHandler handler;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
        initLocationManager();
        initViewModel();
        addLiveDataObservers();

        if(isLocationEnable()){
            if(!hasLocationPermission()) {
                requestToLocationPermission();
            }
        }else{
            requestToEnableProvider();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_LOCATION_PERMISSION: {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initData();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.weather_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initRecyclerViewProperty();
        initEmptyAdapter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(isLocationEnable() && hasLocationPermission()) {
            initData();
        }
    }

    private void initViews(View view){
        mRecyclerView=(RecyclerView)  view.findViewById(R.id.rv_forecast);
        mWeatherLay=(LinearLayout) view.findViewById(R.id.main_weather_lay);
        mCityTextView=(TextView)view.findViewById(R.id.txt_city);
        mTempTextView=(TextView)view.findViewById(R.id.txt_city_temp);
        mErrorLay=(LinearLayout)view.findViewById(R.id.error_lay);
        mProgressView=(ProgressBar) view.findViewById(R.id.progress_img_view);
        mButtonRetry=(Button)view.findViewById(R.id.btn_retry);
        mButtonRetry.setOnClickListener((View.OnClickListener)this);
    }

    private void setVisibility(View view,boolean visibility){
        view.setVisibility(visibility?View.VISIBLE:View.GONE);
    }

    private void showProgressbar(boolean visible){
        setVisibility(mProgressView,visible);
    }

    private void showMainView(boolean visible){
        setVisibility(mWeatherLay,visible);
    }

    private void showErrorView(boolean visible){
        setVisibility(mErrorLay,visible);
    }

    private void handleLoadingResponse(){
        showProgressbar(true);
        showErrorView(false);
        showMainView(false);
    }

    private void handleErrorResponse(){
        showErrorView(true);
        showProgressbar(false);
        showMainView(false);
    }

    private void handleSuccessResponse(Resource resource){
        showProgressbar(false);
        showErrorView(false);
        showMainView(true);
        setCurrentWeatherData((Weather) resource.data);
        setWeatherForcastData((Weather) resource.data);
    }

    private void initData(){
        if(mViewModel!=null && mViewModel.getWeatherLiveData().getValue()!=null) {
            Weather weather = mViewModel.getWeatherLiveData().getValue().data;
            setCurrentWeatherData(weather);
            setWeatherForcastData(weather);
        }else if(mViewModel.getCity().getValue()==null){
            detectLocation();
        }
    }

    private void setCurrentWeatherData(Weather weather){
        if(weather!=null) {
            mCityTextView.setText(weather.getLocation().getName());
            mTempTextView.setText(String.valueOf(weather.getCurrent().getTempC()+" C"));
        }
    }

    private void handleResponse(Resource resource){
        switch (resource.status) {
            case LOADING:
                handleLoadingResponse();
                break;
            case ERROR:
                handleErrorResponse();
                break;
            case SUCCESS:
                handleSuccessResponse(resource);
                break;
        }
    }

    /**
     * Initialising the ViewModel class and are adding the ViewModelFactory class.
     */
    private void initViewModel(){
        mViewModel= ViewModelProviders.of(this,mViewModelFactory).get(WeatherViewModel.class);
        handler=mViewModel.new GeocoderHandler();
        mViewModel.setHandler(handler);
    }

    /**
     * We are observing the LiveData
     */
    private void addWeatherLiveDataObserver(){
       mViewModel.getWeatherLiveData().observe(this, resource->{
           handleResponse(resource);
       });
    }

    /**
     * We are observing location if change then fetch weather details
     */
    private void addLocationObserver(){
        mViewModel.getCity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s!=null){
                    mViewModel.loadWeather(s);
                }
            }
        });
    }

    private void addLiveDataObservers(){
        addWeatherLiveDataObserver();
        addLocationObserver();
    }

    private void detectLocation(){
        mViewModel.detectLocation(isGPSProviderEnabled(mLocationManager),isNetworkProviderEnabled(mLocationManager));
    }

    private void initLocationManager(){
        mLocationManager = (LocationManager) getActivity()
                .getSystemService(LOCATION_SERVICE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_NETWORK_PROVIDER){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void requestToEnableProvider(){
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, REQUEST_CODE_NETWORK_PROVIDER);
    }

    private boolean isLocationEnable(){
        return (isNetworkProviderEnabled(mLocationManager) ||isGPSProviderEnabled(mLocationManager)) &&
                confirmAirplaneModeOff(getActivity());
    }

    // Check that Network Location Provider reports enabled
    private boolean isNetworkProviderEnabled(LocationManager manager) {
        return manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // Check that GPS  Provider reports enabled
    private boolean isGPSProviderEnabled(LocationManager manager) {
        return manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    // Check Airplane Mode - we want airplane mode off
    boolean confirmAirplaneModeOff(Context context) {
        int airplaneSetting =
                Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) ;
        return airplaneSetting == 0;
    }

    private boolean hasLocationPermission(){
        return ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_retry:
                mViewModel.loadWeather(mViewModel.getCity().getValue());
                break;
        }
    }

    private List<Forecastday> getEmptyList(){
        return new ArrayList<>();
    }

    private ForecastAdapter newAdapter(){
        List<Forecastday> list=getEmptyList();
        return new ForecastAdapter(list);
    }

    private void initEmptyAdapter(){
        mAdapter=newAdapter();
    }

    private ForecastAdapter attachDataToAdapter(List<Forecastday> list){
        mAdapter.setList(list);
        return mAdapter;
    }

    private void attachLayoutManagerToRecyclerView(RecyclerView.LayoutManager manager){
        mRecyclerView.setLayoutManager(manager);
    }

    private void attachAdapterToRecyclerView(ForecastAdapter adapter){
       mRecyclerView.setAdapter(adapter);
    }

    private void notifyAdapter(List<Forecastday> list){
        if(mRecyclerView.getAdapter()==null){
           ForecastAdapter adapter=attachDataToAdapter(list);
           attachAdapterToRecyclerView(adapter);
        }else{
            attachDataToAdapter(list);
            mRecyclerView.getAdapter().notifyDataSetChanged();
            runLayoutAnimation(mRecyclerView);
        }
    }

    private void initRecyclerViewProperty(){
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        attachLayoutManagerToRecyclerView(manager);
    }

    private void setWeatherForcastData(Weather weather){
        if(weather==null || weather.getForecast()==null ||
                weather.getForecast().getForecastday()==null ||
                weather.getForecast().getForecastday().size()==0){
            initEmptyAdapter();
        }else{
            List<Forecastday> subList=weather.getForecast().getForecastday().subList(1,
                    weather.getForecast().getForecastday().size());
            notifyAdapter(subList);
        }
    }

    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_from_bottom);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    private void requestToLocationPermission(){
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION_PERMISSION);
    }
}

