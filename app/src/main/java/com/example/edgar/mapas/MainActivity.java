package com.example.edgar.mapas;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends ActionBarActivity implements GoogleMap.OnMapClickListener {

    private final LatLng CASA = new LatLng(18.371104, -100.680355);
    private GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// en esta parte es para obtener el mapa y el tipo de mapa
        mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mapa.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(CASA, 15));
        // esta parte es para ponerle zoom al mapa
        mapa.setMyLocationEnabled(true);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setMyLocationButtonEnabled(true);
        mapa.getUiSettings().setCompassEnabled(true);
        mapa.getUiSettings().setRotateGesturesEnabled(true);
        mapa.getUiSettings().setZoomGesturesEnabled(true);
// esta parte es para agregar un marcador en el itca
        mapa.addMarker(new MarkerOptions().position(CASA).title("ITCA").snippet("Instituto Tecnologico de Cd. Altamirano").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass)).anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);
    }

    public void moveCamera(View view) {
        mapa.moveCamera(CameraUpdateFactory.newLatLng(CASA));
    }
// este es el evento para poder mover la visualizacion del mapa
    public void animateCamera(View view) {
        if (mapa.getMyLocation()!= null)
            mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapa.getMyLocation().getLatitude(),mapa.getMyLocation().getLongitude()),15));
    }

    @Override
    public void onMapClick(LatLng puntoPulsado) {
        mapa.addMarker(new MarkerOptions().position(puntoPulsado).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
// aqui se modifico el action bar para mostrar los diferentes tipos de mapas
        if (id == R.id.action_settings) {
            mapa.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
            return true;
        }
        if (id == R.id.action_settings2) {
            mapa.setMapType((GoogleMap.MAP_TYPE_HYBRID));
            return true;
        }
        if (id == R.id.action_settings3) {
            mapa.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
            return true;
        }
        if (id == R.id.action_settings4) {
            mapa.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            return true;
        }
        if (id == R.id.action_settings5) {
            mapa.setMapType((GoogleMap.MAP_TYPE_NONE));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
