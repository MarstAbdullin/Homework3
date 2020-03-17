package Interpreters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositories.NameRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class Interpreter {

    private InterpretorCommands commands = new InterpretorCommands();
    private Container container = new Container();

    private NameRepository repository;

    @Autowired
    public void setRepository(NameRepository repository) {
        this.repository = repository;
        this.container.addAttribute("nameRepository", this.repository);
    }

    public void handle(String input) {
        String[] temp = input.split(" ");
        String currentCommand = null;
        String[] args = new String[0];

        if (temp.length == 1){
            currentCommand = temp[0];
        } else if (temp.length > 1) {
            currentCommand = temp[0];
            args = Arrays.copyOfRange(temp, 1, temp.length);
        }

        if (currentCommand == null)
            throw new IllegalArgumentException("No command");

        Method[] methods = this.commands.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CmdMapping.class)) {
                String expected = method.getAnnotation(CmdMapping.class).value();
                if (currentCommand.equals(expected)) {
                    method.setAccessible(true);
                    try {
                        method.invoke(commands, container, args);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        method.setAccessible(false);
                        throw new IllegalStateException(e);
                    }
                }
            }
        }
    }


}
