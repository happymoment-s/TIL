# AsyncTask RejectedExecutionException 에러

## 현상

* AsyncTask 사용시 thread 개수가 128개 넘어갈 경우 다음과 같은 에러를 호출하면서 죽는 문제 발생
```Java
java.util.concurrent.RejectedExecutionException: Task android.os.AsyncTask$3@42212328 rejected from
 java.util.concurrent.ThreadPoolExecutor@414b86b8[Running, pool size = 128, active threads = 128, queued tasks = 10, completed tasks = 475]

```

* 기존 AsyncTask 내부 소스
```JAVA
private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };

    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);

    /**
     * An {@link Executor} that can be used to execute tasks in parallel.
     */
    public static final Executor THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
                    TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
```

* AsyncTask executeOnExecutor 사용시 executeOnExecutor에 다음과 같이 기존 AsyncTask.THREAD_POOL_EXECUTOR 사용
```Java
executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
```


## 해결방안
* 동일한 ThreadFactory 를 만들되, sPoolWorkQueue 의 사이즈를 128보다 크게 설정
```
private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE = 1;

    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(1024);

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };

    private final Executor THREAD_POOL_EXECUTOR
            = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,
            TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
```

## 참고사이트
* [https://gist.github.com/benelog/5954649](https://gist.github.com/benelog/5954649)