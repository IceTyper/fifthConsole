package org.example.commands;
import org.example.Exceptions.RedundantArguments;
import org.example.important.Core;
import org.example.interfaces.Command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class Save implements Command {
    @Override
    public String getDescription() {
        return "save - сохранение коллекции в файл";
    }

    @Override
    public void execute(Core core, String[] args) {
        try {
            if (args.length > 1) {
                throw new RedundantArguments();
            }
            core.getFileManager().saveToFile(new File("C:/Users/fmusa/IdeaProjects/fifthConsole/src/main/resources/collection.json"), core.getCollectionManager());
        } catch (RedundantArguments e) {
            System.out.println(e.printProblem(args));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
