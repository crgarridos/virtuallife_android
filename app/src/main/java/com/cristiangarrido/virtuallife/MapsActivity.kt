package com.cristiangarrido.virtuallife

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng


/**
 * Created by cristian on 10/02/17.
 */
class MapsActivity : FragmentActivity(), OnMapReadyCallback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        map.getUiSettings().setCompassEnabled(false)
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL)
        val cameraPosition = CameraPosition.Builder()
                .target(LatLng(39.87266, -4.028275))
                .zoom(18f)
                .tilt(67.5f)
                .bearing(314f)
                .build()
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}