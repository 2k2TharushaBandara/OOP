package arraysumwiththreads;

import java.util.Random;

public class ArraySumWithThreads {
    private static final int sizeOfArray = 100000000;
    private static final int numOfThreads = 4;

        // Thread class to calculate sum of a portion of the array
    static class SumThread extends Thread {
        private int[] array;
        private int startIndex;
        private int endIndex;
        private int sum;

        public SumThread(int[] array, int startIndex, int endIndex) {
            this.array = array;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            for (int i = startIndex; i < endIndex; i++) {
                sum += array[i];
            }
        }

        public int getSum() {
            return sum;
        }
    }
    
    // Generate random array
    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100); //0-99
        }
        return array;
    }

    //Single threaded execution
    private static int calculateSum(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

    //Multiple threaded execution
    private static int calculateSumWithThreads(int[] array, int numThreads) {
        int segmentSize = array.length / numThreads;
        
        SumThread[] threadsArray = new SumThread[numThreads];//creating a thread array
        
        for (int i=0; i<numThreads; i++) {
            int startIndex = i*segmentSize;
            int endIndex =(i==numThreads-1) ? array.length : (i+1)*segmentSize;
            threadsArray[i] = new SumThread(array, startIndex, endIndex);
            threadsArray[i].start();
        }
        int totalSum = 0;
        try {
            for (SumThread thread : threadsArray) {
                thread.join();
                totalSum += thread.getSum();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return totalSum;
    }
    
    public static void main(String[] args) {
        int[] array = generateRandomArray(sizeOfArray);
        long startTime, endTime;

        // Single-threaded calculation
        startTime = System.currentTimeMillis();
        int sumSingleThread = calculateSum(array);
        endTime = System.currentTimeMillis();
        System.out.println("Single-threaded sum: " + sumSingleThread);
        System.out.println("Single-threaded execution time: " + (endTime - startTime) + " milliseconds");

        // Multi-threaded calculation
        startTime = System.currentTimeMillis();
        int sumMultiThread = calculateSumWithThreads(array, numOfThreads);
        endTime = System.currentTimeMillis();
        System.out.println("Multi-threaded sum: " + sumMultiThread);
        System.out.println("Multi-threaded execution time: " + (endTime - startTime) + " milliseconds");
    }

}


//When n is increased, multithreading is effective
