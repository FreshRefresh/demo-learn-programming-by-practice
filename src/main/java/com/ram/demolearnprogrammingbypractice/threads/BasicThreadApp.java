package com.ram.demolearnprogrammingbypractice.threads;

public class BasicThreadApp {

  public static void main(String[] args) throws InterruptedException {

    Thread t1 = new Thread(new TaskRunnableType());
    Thread t2 = new TaskThreadType();

    t1.start();
    t2.start();

    // Join main thread with JVM Thread so that application will not terminate.
    Thread.currentThread().join();
  }


  public static class TaskRunnableType implements Runnable {

    @Override
    public void run() {
      System.out.println("I am executing TaskRunnableType task. Tread Name :  " + Thread.currentThread().getName());
    }
  }


  public static class TaskThreadType extends  Thread{

    @Override
    public void run() {
      super.run();
      System.out.println("I am executing TaskThreadType task. Tread Name :  " + Thread.currentThread().getName());
    }
  }
}
