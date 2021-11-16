package edu.fullerton.ecs.mdap.intersectioninfo.geocoding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.fullerton.ecs.mdap.intersectioninfo.services.MapboxService
import kotlinx.coroutines.launch

/**
 * ViewModel for data displayed on the map fragment.
 */
class GeocodingViewModel: ViewModel() {

    // Backing property for address
    private val _address = MutableLiveData("")

    // Valid address returned by MapBox.
    val address: LiveData<String>
        get() {
            return _address
        }

    // Backing property for imageURL
    private val _imageURL = MutableLiveData("")

    // URL of the address' image
    val imageURL: LiveData<String>
        get() {
            return _imageURL
        }

    /**
     * Calls Mapbox's Geocoding API to retrieve the closest matching
     * address for the given search string
     *
     * @param searchString string to match to an address
     */
    fun geoCode(searchString: String) {
        // Run API request as a corotuine to not block the application.
        viewModelScope.launch {
            try {
                val place = MapboxService.GeoCoding.Api.retrofitService.getPlaces(searchString)
                // Use the name, longitude, and latitude of the first matched place
                _address.value = place.features[0].place_name
                var longitude = place.features[0].center[0]
                var latitude = place.features[0].center[1]

                var zoom = 15
                var rotation = 0
                // Create a URL using the retrieved values. The URL follows the Static Images API
                // format.
                _imageURL.value = "https://api.mapbox.com/styles/v1/mapbox/streets-v11/static/${longitude},${latitude},${zoom},${rotation}/300x200?access_token=${MapboxService.ACCESS_TOKEN}"
            } catch (e: Exception) {
                _address.value = "Failure: ${e.message}"
            }
        }
    }
}