package com.block5Logging.block5Logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Block5LoggingApplication {

	private static final Logger logger = Logger.getLogger(Block5LoggingApplication.class.getName());

	public static void main(String[] args) {
		try {
			// Configurar el manejador de archivo para el log de tipo "Error"
			FileHandler fileHandler = new FileHandler("error.log");
			fileHandler.setLevel(Level.SEVERE);
			logger.addHandler(fileHandler);

			// Ejemplo de logs
			logger.severe("Este es un mensaje de Error");
			logger.warning("Este es un mensaje de Warning");
			logger.info("Este es un mensaje de Info");
			logger.fine("Este es un mensaje de Fine");
			logger.finer("Este es un mensaje de Finer");
			logger.finest("Este es un mensaje de Finest");

			// Configurar el nivel de log para la consola
			logger.setLevel(Level.WARNING);

			// Ejemplo de logs en la consola
			logger.severe("Este es un mensaje de Error");
			logger.warning("Este es un mensaje de Warning");
			logger.info("Este es un mensaje de Info");
			logger.fine("Este es un mensaje de Fine");
			logger.finer("Este es un mensaje de Finer");
			logger.finest("Este es un mensaje de Finest");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
