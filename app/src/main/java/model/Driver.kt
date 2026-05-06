package com.example.worldoff1.model

data class DriverResponse(
    val MRData: MRData
)

data class MRData(
    val DriverTable: DriverTable
)

data class DriverTable(
    val Drivers: List<Driver>
)

data class Driver(
    val givenName: String,
    val familyName: String,
    val nationality: String
)