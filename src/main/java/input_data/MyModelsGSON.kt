package input_data

import java.util.Date

data class DataWithNulls_G(
    val requestedAt:Date?,
    val planets: ArrayList<PlanetsWithNulls_G>?
){
    data class PlanetsWithNulls_G(
        val name:String?,
        val founder:String?,
        val population:Long?,
        val distFromSun:Double?,
        val moons:ArrayList<MoonWithNulls_G>?
    ){
        data class MoonWithNulls_G(
            val name:String?,
            val founder:String?,
        )
    }
}


data class DataWithDefaults_G(
    val requestedAt:Date = Date(),
    val planets: ArrayList<PlanetsWithDefaults_G> = arrayListOf()
){
    data class PlanetsWithDefaults_G(
        val name:String = "PLANET_NAME",
        val founder:String? = null,
        val population:Long = 0,
        val distFromSun:Double = 0.0,
        val moons:ArrayList<MoonWithDefaults_G> = arrayListOf()
    ){
        data class MoonWithDefaults_G(
            val name:String = "MOON_NAME",
            val founder:String = "MOON_FOUNDER",
        )
    }
}


