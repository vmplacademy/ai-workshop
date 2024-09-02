package pl.vm.aiworkshop;

import org.springframework.boot.SpringApplication;

public class ToDoListLocalApplication {

    public static void main(String[] args) {
        SpringApplication
                .from(ToDoListApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
