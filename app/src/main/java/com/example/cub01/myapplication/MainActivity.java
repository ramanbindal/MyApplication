package com.example.cub01.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

        findViewById(R.id.activity_main_btn_async_even)
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


        findViewById(R.id.activity_main_btn_async_odd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Observable<Integer> list=source.getData();
                    list.subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .filter(new Predicate<Integer>() {
                                @Override
                                public boolean test(@NonNull Integer integer) throws Exception {
                                    return integer % 2 != 0;
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
                                    Log.e("testing", ""+d.toString());

                                }

                                @Override
                                public void onNext(@NonNull String s) {
                                    Log.e("testing", "" + s);
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


        findViewById(R.id.activity_main_btn_async_string).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable<String> strings=source.getStrings();
                strings.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Log.d("testing",""+d.toString());

                            }

                            @Override
                            public void onNext(@NonNull String s) {
                                Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.d("testing","onError");

                            }

                            @Override
                            public void onComplete() {
                                Log.d("testing","onComplete");
                            }
                        });
            }
        });

        findViewById(R.id.activity_main_btn_async_int).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable<Integer> intg=source.getInt();
                intg.subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Integer integer) {
                                Toast.makeText(MainActivity.this, "adfaf"+integer, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });

    }
}
