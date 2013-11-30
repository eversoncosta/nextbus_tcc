package pacote.NextBus10;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Gps implements LocationListener {
	
	private Location location;
	
	 public Gps(Context c) {
	        location = null;
	        LocationManager locationManager = (LocationManager) c.getSystemService(c.LOCATION_SERVICE);
	        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	    }
	 
	    public void onLocationChanged(Location location) {
	        this.location = location;
	 
	    //Este objeto location que ira retornar as coordenadas:
	    //double latitute = location.getLatitude();
	    //double longitude = location.getLongitude();
	    }
	 
	    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
	        //chamado quando o estado muda de Provider. Status poss�veis: OUT_OF_SERVICE, TEMPORARILY_UNAVAILABLE ou AVAILABLE.
	    }
	 
	    public void onProviderEnabled(String arg0) {
	    //chamado quando o provedor � ativada pelo usu�rio
	    }
	 
	    public void onProviderDisabled(String arg0) {
	       //chamado quando o provedor � desativado pelo usu�rio, se ele j� estiver desativado, � chamado imediatamente ap�s requestLocationUpdates
	    }
	 
	    public Location getLocation() {
	        return location;
	    }

}
