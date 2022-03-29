package com.ram.demolearnprogrammingbypractice.threads;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class CompletableFeaturesApp {

  public static ForkJoinPool forkJoinPool = new ForkJoinPool(10);

  public static void print(int value) {
    System.out.println("print Thread : " + Thread.currentThread());
    System.out.println("Value - " + value);
  }

  public static int compute() {
    System.out.println("compute Thread : " + Thread.currentThread());
    return 2;
  }

  public static CompletableFuture<Integer> create() {
    System.out.println("CompletableFuture Thread - " + Thread.currentThread());
    return CompletableFuture.supplyAsync(() -> compute(), forkJoinPool);
  }

  public static boolean sleep(long ms) {
    try {
      Thread.sleep(ms);
      return true;
    } catch (Exception ex) {
      return false;
    }
  }

  public static void main(String[] args) {
    CompletableFuture<Integer> cf = create();
    sleep(1000);
    cf.thenAccept(data -> print(data));
    sleep(1000);
  }
}
