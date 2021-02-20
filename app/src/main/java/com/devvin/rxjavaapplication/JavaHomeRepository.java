package com.devvin.rxjavaapplication;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

class JavaHomeRepository {
    private String someText = "text";
    private Observable<String> observable;
    private Observer<String> observer;

    public void testRxJava(){
        observable = Observable.just(someText);

        observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull String s) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
