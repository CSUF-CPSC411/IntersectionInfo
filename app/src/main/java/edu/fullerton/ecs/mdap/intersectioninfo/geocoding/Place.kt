package edu.fullerton.ecs.mdap.intersectioninfo.geocoding

/**
 * Data class representation of the JSON structure. It does not need
 * to completely represent the JSON structure. You can include only the
 * attributes you need. All other attributes are ignored. Take note that it
 * should still follow the same structure as the JSON. For example, the names
 * of the attributes, the type of the attribute, and how they are nested.
 */

// A Feature object that contains a name and a center (longitude, latitude).
data class Feature(
    val place_name: String,
    val center: Array<Double>,
)


// Represents a Place root object that contains an array of Feature objects
data class Place (
    val features: Array<Feature>
)