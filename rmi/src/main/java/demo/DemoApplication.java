package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@SpringBootApplication
public class DemoApplication {

    @Bean
    MessageService messageService() {
        return new SimpleMessageService();
    }

    @Bean(name = "/messageService")
    HttpInvokerServiceExporter httpMessageService( ) {
        HttpInvokerServiceExporter http = new HttpInvokerServiceExporter();
        http.setServiceInterface(MessageService.class);
        http.setService( this.messageService());
        return http;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);
    }
}


class SimpleMessageService
        implements MessageService {

    @Override
    public Message greet(String n) {
        return new Message("Hello, " + n + "!");
    }
}