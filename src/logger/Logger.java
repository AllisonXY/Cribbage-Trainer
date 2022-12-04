package logger;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import event.Event;

public class Logger {
	File log;
	BufferedWriter fileWriter;
	
	public Logger() {
		try {
			File log = new File("cribbage.log");
			
			// if file already exists, wipe
			this.log = log;
			
			FileWriter fw = new FileWriter(this.log, false);
			this.fileWriter = new BufferedWriter(fw);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// Takes an event e and logs its toString
	public void logEvent(Event e) {
		
		try {
			System.out.println("Try\n");
			fileWriter.write(e.toString() + "\n");
			
		} catch (IOException e1) {
			System.out.println("Catch\n");
			e1.printStackTrace();
		}
		
		// delete validation line
		System.out.println(e.toString());
	}
	
	//close the log file
	public void closeLog() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
