//Copyright (c) Microsoft Corporation. All rights reserved.
//Licensed under the MIT License.
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class TestConsumer {


    //Change constant to send messages to the desired topic
    private final static String TOPIC = "eh-java-kafka-fc";
    
    private final static int NUM_THREADS = 1;

    public static void main(String... args) throws Exception {

        // Logger logger = LoggerFactory.getLogger(TestConsumer.class);
        // logger.info("Hello World");
        final ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        for (int i = 0; i < NUM_THREADS; i++){
            executorService.execute(new TestConsumerThread(TOPIC));
        }

        System.out.println("eh-java-kafka-fc");
    }
}
