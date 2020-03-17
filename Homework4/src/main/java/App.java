import Interpreters.Interpreter;
import config.ApplicationContextConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);

        Interpreter interpreter = context.getBean(Interpreter.class);

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equals("stop")){
            interpreter.handle(input);
        }

    }
}
