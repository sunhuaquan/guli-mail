package com.sun.mail.test.threads;

public class FutureCallback<T> {

    public T data;


    public void complete(T value) {
        this.data=value;
        synchronized (this) {
            this.notifyAll();
        }
    }

    public T getResult(Long timeout) {

        if (data != null) {
            return data;
        }
        synchronized (this) {
            try {
                this.wait(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
            }
        }
        return data;

    }

    public static void main(String[] args){

        FutureCallback<String> callback=new FutureCallback<>();
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                callback.complete("hahaha");
            }
        }.start();
        String result = callback.getResult(5000L);
        System.out.println(result);


    }
}
