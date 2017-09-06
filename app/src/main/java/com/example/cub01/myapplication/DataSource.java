package com.example.cub01.myapplication;


import android.renderscript.ScriptGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.observers.FutureObserver;

/**
 * Created by cub01 on 9/6/2017.
 */

public class DataSource {


    public List<Integer> getData2() throws InterruptedException {
        Thread.sleep(5000);
        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        return data;
    }

    public Observable<Integer> getData() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                Thread.sleep(5000);
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                //e.onError(new IOException());
                e.onNext(4);
                e.onNext(5);
                e.onComplete();
            }
        });
    }

    public Observable<String> getStrings(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("Ganesh");
                e.onNext("Mukul");
                e.onNext("Aman");
                e.onNext("Harsh");
                e.onNext("Monika");
                e.onComplete();
            }
        });
    }

    public Observable<Integer> getInt(){
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                e.onNext(234);
                e.onComplete();
            }
        });
    }
}
