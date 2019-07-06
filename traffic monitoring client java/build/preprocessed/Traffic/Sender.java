package Traffic;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suha
 */


import java.io.IOException;
import java.io.OutputStream;

public class Sender extends Thread {
    private OutputStream os;

    private String message;

    public Sender(OutputStream os) {
	this.os = os;
	start();
    }

    public synchronized void send(String msg) {
	message = msg;
	notify();
    }

    public synchronized void run() {
	while (true) {
	    // If no client to deal, wait until one connects
	    if (message == null) {
		try {
		    wait();
		} catch (InterruptedException e) {
		}
	    }

	    if (message == null) {
		break;
	    }

	    try {
		os.write(message.getBytes());
		os.write("\r\n".getBytes());
	    } catch (IOException ioe) {
		ioe.printStackTrace();
	    }

	    // Completed client handling, return handler to pool and
	    // mark for wait
	    message = null;
	}
    }

    public synchronized void stop() {
	message = null;
	notify();
    }
}
