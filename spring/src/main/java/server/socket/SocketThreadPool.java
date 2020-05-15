package server.socket;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 处理Socket请求的线程池
 * @author jianming
 * @create 2020-05-15-19:04
 */
public class SocketThreadPool {

    /**
     * 线程池基本大小
     */
    private int corePollSize = 20;

    /**
     * 线程池最大大小
     */
    private int maximumPoolSize = 20;

    /**
     * 线程池线程保活时间
     */
    private int keepAliveTime = 10;

    /**
     * 保活时间单位
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    /**
     * 任务队列
     */
    private BlockingQueue workQueue = new ArrayBlockingQueue(10);

    /**
     * 饱和策略
     */
    private ThreadPoolExecutor.CallerRunsPolicy RejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();

    /**
     * 线程id
     */
    private volatile int threadIndex = 0;

    /**
     * 线程池
     */
    private ThreadPoolExecutor socketThreadPool = new ThreadPoolExecutor(corePollSize, maximumPoolSize, keepAliveTime, timeUnit,
            workQueue, (r) -> {
        // 创建线程的工厂
                synchronized (this) {
                    threadIndex++;
                }
                Thread thread = new Thread(r);
                thread.setName("JM socket thread " + threadIndex);
                return thread;
            },
            RejectedExecutionHandler);

    /**
     * 提交任务
     * @param r
     */
    public void execute(Runnable r) {
        socketThreadPool.execute(r);
    }

    public Future<?> submit(Runnable r) {
        Future<?> submit = socketThreadPool.submit(r);
        return submit;
    }

    public Future submit(Callable call) {
        Future submit = socketThreadPool.submit(call);
        return submit;
    }





}
