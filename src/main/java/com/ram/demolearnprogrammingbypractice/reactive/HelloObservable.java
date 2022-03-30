package com.ram.demolearnprogrammingbypractice.reactive;

import io.reactivex.Observable;

public class HelloObservable {

  public static void main(String[] args) {

    Observable<Integer> observable = Observable.create(observableEmitter -> {
      observableEmitter.onNext(1);
      observableEmitter.onNext(2);
      observableEmitter.onNext(3);
      observableEmitter.onNext(4);
      observableEmitter.onNext(5);
      observableEmitter.onNext(6);
    });

    observable.subscribe(element -> {
      System.out.println("Thread - " + Thread.currentThread().getName());
      System.out.println("Element - " + element);
    });
  }
}
