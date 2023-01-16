import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ir.ha.practice.ApplicationLoader

//import android.content.pm.PackageManager
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import java.util.jar.Manifest
//
//private fun getUserLocation() {
//    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//        ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), REQ_CODE)
//    } else {
//        location()
//    }
//}
//
//@SuppressLint("MissingPermission")
//private fun location(){
//
//    fusedLocationProviderClient.lastLocation.addOnCompleteListener {
//        val location = it.result
//        val geoCoder = Geocoder(this@MapsActivity, Locale.getDefault())
//
//        if (location != null) {
//            try {
//                val listAddress = geoCoder.getFromLocation(location.latitude,location.longitude,1)
//                latitude = listAddress[0].latitude
//                longitude = listAddress[0].longitude
//
//                val userLatLng = LatLng(latitude,longitude)
//                mMap.addMarker(MarkerOptions().position(userLatLng).title("Your Current Location")).showInfoWindow()
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLng,13f))
//
//                //Visible Submit UserLocation
//                requestMyLocationBtn.visibility = View.VISIBLE
//
//            } catch (ex: IOException) {
//                ex.printStackTrace()
//            }
//        }
//
//    }
//}