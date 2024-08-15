package com.upchina.spring.file.watch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.nio.file.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by anjunli on  2024/8/15
 **/
@Component
public class FileWatch implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("start to watch file changes");
        //实例化WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        String url = "E:\\hdfs";
        Path path = Paths.get(url);
        //注册监听事件
        path.register(watchService,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_CREATE);
        //新建定时任务线程池，仅作为示例使用，实际项目请按标准使用。
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);
        threadPool.scheduleAtFixedRate(
                () -> {
                    Thread.currentThread().setName("watch-dog-" + Thread.currentThread().getId());
                    //获取监听结果，没有返回null
                    WatchKey key = watchService.poll();
                    if (key == null) {
                        System.out.println("文件无变化");
                        return;
                    }
                    //利用 key.pollEvents() 方法返回一系列的事件列表
                    for (WatchEvent<?> event : key.pollEvents()) {
                        //得到 监听的事件类型
                        WatchEvent.Kind<?> kind = event.kind();
                        if (kind.name().equalsIgnoreCase(StandardWatchEventKinds.ENTRY_CREATE.name())) {
                            //业务逻辑代码
                            System.out.println("文件新增");
                        }
                        Path pathName = (Path) event.context();
                        System.out.println(kind.name() + " pathName = " + pathName);
                        //每次的到新的事件后，需要重置监听池
                        key.reset();
                    }
                }, 1000, 10000, TimeUnit.MILLISECONDS);
    }
}
