//package com.sandbox.demo;
//
//import com.alibaba.jvm.sandbox.api.Information;
//import com.alibaba.jvm.sandbox.api.Module;
//import com.alibaba.jvm.sandbox.api.ProcessController;
//import com.alibaba.jvm.sandbox.api.annotation.Command;
//import com.alibaba.jvm.sandbox.api.listener.ext.Advice;
//import com.alibaba.jvm.sandbox.api.listener.ext.AdviceListener;
//import com.alibaba.jvm.sandbox.api.listener.ext.EventWatchBuilder;
//import com.alibaba.jvm.sandbox.api.resource.ModuleEventWatcher;
//import org.kohsuke.MetaInfServices;
//
//import javax.annotation.Resource;
//
///**
// * @author 冷枫红舞
// */
//@MetaInfServices(Module.class)
//@Information(id = "broken-clock-tinker")
//public class MySandBoxDemo implements Module {
//
//    @Resource
//    private ModuleEventWatcher moduleEventWatcher;
//
//    @Command("repairCheckState")
//    public void repairCheckState() {
//
//        new EventWatchBuilder(moduleEventWatcher)
//                .onClass("com.clock.Clock")
//                .onBehavior("checkState")
//                .onWatch(new AdviceListener() {
//
//                    @Override
//                    protected void afterThrowing(Advice advice) throws Throwable {
//                        ProcessController.returnImmediately(null);
//                    }
//                });
//
//    }
//}
