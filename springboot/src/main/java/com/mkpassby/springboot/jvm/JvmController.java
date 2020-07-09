package com.mkpassby.springboot.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: springboot
 * @description:
 * @author: mk_passby
 * @create: 2020-02-12 10:06
 **/
@RestController
public class JvmController {
    @GetMapping("/getJVM")
    public String getJVM(){
        List<GarbageCollectorMXBean> garbageCollectorMXBeanList= ManagementFactory.getGarbageCollectorMXBeans();
        StringBuilder stringBuilder=new StringBuilder();
        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeanList) {
            stringBuilder.append(garbageCollectorMXBean.getName()+System.lineSeparator());
        }
        return stringBuilder.toString();
    }
    @GetMapping("jvmlock")
    public void deadLock(){
        DefineDeadLock defineDeadLock=new DefineDeadLock();
        Thread1 thread1=new Thread1(defineDeadLock);
        Thread2 thread2=new Thread2(defineDeadLock);
        thread1.start();
        thread2.start();
    }

    public class Thread1 extends Thread{
        private DefineDeadLock d1;

        public Thread1(DefineDeadLock d1) {
            this.d1 = d1;
        }
        @Override
        public void run(){
            try {
                d1.getOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public class Thread2 extends Thread{
        private DefineDeadLock defineDeadLock;

        public Thread2(DefineDeadLock defineDeadLock) {
            this.defineDeadLock = defineDeadLock;
        }

        @Override
        public void run() {
            try {
                defineDeadLock.getTwo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
