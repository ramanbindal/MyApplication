package com.example.cub01.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    DataSource source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        source = new DataSource();

        findViewById(R.id.activity_main_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Observable<Integer> list = source.getData();
                        list.subscribeOn(Schedulers.computation())
                                .observeOn(AndroidSchedulers.mainThread())
                                .filter(new Predicate<Integer>() {
                                    @Override
                                    public boolean test(@NonNull Integer integer) throws Exception {
                                        return integer % 2 == 0;
                                    }
                                })
                                .map(new Function<Integer, String>() {
                                    @Override
                                    public String apply(@NonNull Integer integer) throws Exception {
                                        return "Hello " + integer;
                                    }
                                })
                                .subscribe(new Observer<String>() {
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {
                                        Log.e("testing", "" + d);
                                    }

                                    @Override
                                    public void onNext(@NonNull String integer) {
                                        Log.e("testing", "" + integer);
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Log.e("testing", "onError");
                                    }

                                    @Override
                                    public void onComplete() {
                                        Log.e("testing", "onComplete");
                                    }
                                });
                    }
                });

        findViewById(R.id.activity_main_btn2)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            List<Integer> list = source.getData2();
                            Log.e("testing", "" + list.toString());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
