package com.chiratsxki.spaceconsole;

import com.chiratsxki.spaceconsole.model.Asteroids;

public class CommandProcessor {

    public static String processCommand(String command) {

        String finalCommand = command.toLowerCase();

        if (finalCommand.equals("help")) {
            return "Asteroid - obtaining data about asteroid.\n" +
                    "           exit - to force close the program";
        } else if (finalCommand.equals("exit")) {
            System.exit(0);
        } else if (finalCommand.equals("asteroid")){
            return Asteroids.processAsteroidsCommand();
        } else {
            return "Unknown command. Type [help] for a list of available commands.";
        }
        return null;
    }
}
