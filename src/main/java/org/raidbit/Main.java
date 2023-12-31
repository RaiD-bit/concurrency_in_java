package org.raidbit;

import java.util.Random;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int queueSize = 2;
        DevaQueue<Integer> q = new DevaQueue<>(queueSize);
        Thread producer = new Thread(()->{
            // lets push an element in the queue every 5 s
            int k = 100;
            while (k > 0){
                int randomInt = random.nextInt(100);
                q.enqueue(randomInt);
                System.out.println("last element pushed is " + randomInt);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // handle
                }
                k--;
            }
        });

        Thread consumer = new Thread(()->{
            // lets deque an element in the queue every 5 s
            int k = 100;
            while(k>0){

                Integer element = q.dequeue();
                System.out.println("last element polled is " + element);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // handle
                }
                k--;
            }
        });

        producer.start();
        consumer.start();
    }
}