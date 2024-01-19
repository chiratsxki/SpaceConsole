package com.chiratsxki.spaceconsole;

import com.chiratsxki.spaceconsole.model.Asteroids;
import com.chiratsxki.spaceconsole.model.Horizons;

public class CommandProcessor {

    public static String processCommand(String command) {

        String finalCommand = command.toLowerCase();

        if (finalCommand.equals("help")) {
            return "Asteroid - obtaining data about asteroid.\n" +
                    "Horizon - Horizons on-line solar system data and ephemeris computation service provides access to key solar system data and flexible production of highly accurate ephemerides for solar system objects" +
                    "           exit - to force close the program";
        } else if (finalCommand.equals("exit")) {
            System.exit(0);
        } else if (finalCommand.equals("asteroid")){
            return Asteroids.processAsteroidsCommand();
        } else if (finalCommand.equals("horizons")) {
            return Horizons.processHorizonCommand();
        } else {
            return "Unknown command. Type [help] for a list of available commands.";
        }
        return null;
    }
}
