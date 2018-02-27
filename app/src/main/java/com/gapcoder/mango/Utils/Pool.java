package com.gapcoder.mango.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by suxiaohui on 2018/2/26.
 */

public class Pool {

    private static ExecutorService excutor= Executors.newFixedThreadPool(1);

    public static void run(Runnable r){

        excutor.execute(r);

    }
}
