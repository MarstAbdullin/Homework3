package Interpreters;

import models.Name;
import repositories.NameRepository;

import java.util.ArrayList;
import java.util.List;

public class InterpretorCommands {

        @CmdMapping("add")
        private void goTo(Container container, String[] args) {
            if (args.length != 1)
                throw new IllegalArgumentException("Not enough arguments");
            String currentName = args[0];
            NameRepository nameRepository = container.getAttribute("nameRepository", NameRepository.class);

            Name name = Name.builder()
                    .name(currentName)
                    .build();

            nameRepository.save(name);
        }

        @CmdMapping("out")
        private void out(Container container, String[] args) {
            if (args.length != 0)
                throw new IllegalArgumentException("Not enough arguments");
            NameRepository nameRepository = container.getAttribute("nameRepository", NameRepository.class);
            List<Name> nameEntries = new ArrayList<>();
            nameRepository.findAll().forEach(nameEntries::add);
            System.out.println(nameEntries);
        }
}
