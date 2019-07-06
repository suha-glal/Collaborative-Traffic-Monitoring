package Traffic;
import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.location.*;
//i added this
import java.io.OutputStream;
import javax.microedition.io.*;//ConnectionNotFoundException;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
/////////////////
public class TrafficMonitoring extends MIDlet implements CommandListener , LocationListener {

  private Display display;
  private Form form;
  private Command exit = new Command("Exit", Command.EXIT, 1);
  private Command start = new Command("Start", Command.SCREEN, 1);
  private Command stop = new Command("Stop", Command.SCREEN, 1);
   private Command map = new Command("map", Command.SCREEN, 1);
 // private TextField interval =
  //  new TextField("Update Interval(sec)","1", 5, TextField.NUMERIC);
  private int sec = 1;
  private int i = 0;
  private int speed = 0;

  private StringItem info = new StringItem("Location:","unknown");
  private StringItem senddata = new StringItem("","");
 Coordinates f=new Coordinates(0,0,0);
private LocationProvider locationProvider = null;
SocketConnection sc=null;
OutputStream os;

  public TrafficMonitoring(){
    display = Display.getDisplay(this);
    form = new Form("Traffic Monitoring");
    form.addCommand(exit);
    form.addCommand(start);
    form.addCommand(map);
    form.setCommandListener(this);
    form.append(info);

   try {
      locationProvider = LocationProvider.getInstance(null);
    } catch (Exception e) {
      exit();
    }


  }//trackMe
public void commandAction(Command c, Displayable s) {
    if ((c == Alert.DISMISS_COMMAND)) {
	    notifyDestroyed();
	   // try{destroyApp(true);}catch (e)
	}
    if (c == exit) {
      exit();
    }
    if(c == start){
      form.removeCommand(start);

       sec = 1;

	// Start querying GPS data :
	new Thread(){
     public void run(){
          locationProvider.setLocationListener(TrafficMonitoring.this, sec, -1, -1);
          connect();

	 }
	}.start();

      form.addCommand(stop);
    }
    if(c == stop){
      form.removeCommand(stop);

      // Stop querying GPS data :
	new Thread(){
        public void run(){
          locationProvider.setLocationListener(null, -1, -1, -1);
	 }
     }.start();
      form.addCommand(start);
    }
    if(c== map)
    {
            try{
              platformRequest("http://localhost/GPS%20of%20GMAP/frmgmapmain.php");
              exit();
                }catch(Exception e){}
    }//c==map
  }//command action
public void showSplashScreen(
                     Display d, Displayable next ){
    Image logo = null;

    try {
      logo = Image.createImage("/image/logo.png" );
    }
    catch( IOException e ){
    }

    Alert a = new Alert( "Traffic Monitoring",
           "Traffic Monitoring is a mobile application that allows GPS-equipped mobile phones to automatically share their location and speed data while driving on the roads. The data is collected by a central service from all participating vehicles and is continuously processed in order to infer the traffic flow. The output will be displayed on Google maps with color-coding on streets reflecting the traffic conditions and speed of flow on individual streets. The displayed maps convey real-time traffic information on demand in a usable way to drivers. ",
            logo, AlertType.INFO );
    a.setTimeout( Alert.FOREVER );
    display.setCurrent(a, next );
}

public void locationUpdated(LocationProvider provider, Location location){
  if (location != null && location.isValid()) {
      i++;

    QualifiedCoordinates qc = location.getQualifiedCoordinates();
    if(i>1)
      {
      speed=    (int) ((f.distance(qc)) / sec);
      }
      info.setText(
        "Lat: " + qc.getLatitude() + "\n" +
        "Lon: " + qc.getLongitude() + "\n" +
        "Alt: " + qc.getAltitude() + "\n"
      );
       senddata.setText(""+qc.getLatitude()+"@"+qc.getLongitude()+"#"+speed+"$");
     new Sender(os).send(senddata.getText());

         f.setLatitude(qc.getLatitude());
         f.setLongitude(qc.getLongitude());

  }

}

  public void providerStateChanged(LocationProvider provider,
                                   int newState){
  }
void connect()
{
    ///i added this//////////////////////////////////////


        try{
sc=(SocketConnection) Connector.open("socket://localhost:8000");
     os = sc.openOutputStream();

	    // Start the thread for sending messages - see Sender's main
	    // comment for explanation

   //  new Sender(os).send(info.getText());

        }catch (ConnectionNotFoundException cnfe) {
	    Alert a = new Alert("Client",
		    "Please run Server MIDlet first on port 8000",
		    null, AlertType.ERROR);
	    a.setTimeout(Alert.FOREVER);
	    a.setCommandListener(this);
	    display.setCurrent(a);
        
	} catch (Exception e) {
	    e.printStackTrace();
	}
}

       


  public void startApp () {
      showSplashScreen(display,form);
   
  }

  public void pauseApp () {}

  public void destroyApp (boolean forced) {}

  public void exit(){
    destroyApp(false);
    notifyDestroyed();
  }

}