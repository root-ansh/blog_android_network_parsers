package input_data

import java.util.Date

data class DataWithNulls_S(
    val requestedAt:Date?,
    val planets: ArrayList<PlanetsWithNulls_S>?
){
    data class PlanetsWithNulls_S(
        val name:String?,
        val founder:String?,
        val population:Long?,
        val distFromSun:Double?,
        val moons:ArrayList<MoonWithNulls_S>?
    ){
        data class MoonWithNulls_S(
            val name:String?,
            val founder:String?,
        )
    }
}


data class DataWithDefaults_S(
    val requestedAt:Date = Date(),
    val planets: ArrayList<PlanetsWithDefaults_S> = arrayListOf()
){
    data class PlanetsWithDefaults_S(
        val name:String = "PLANET_NAME",
        val founder:String? = null,
        val population:Long = 0,
        val distFromSun:Double = 0.0,
        val moons:ArrayList<MoonWithDefaults_S> = arrayListOf()
    ){
        data class MoonWithDefaults_S(
            val name:String = "MOON_NAME",
            val founder:String = "MOON_FOUNDER",
        )
    }
}


