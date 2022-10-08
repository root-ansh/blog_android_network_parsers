package input_data

import java.util.Date

data class DataWithNulls(
    val requestedAt:Date?,
    val planets: ArrayList<PlanetsWithNulls>?
){
    data class PlanetsWithNulls(
        val name:String?,
        val founder:String?,
        val population:Long?,
        val distFromSun:Double?,
        val moons:ArrayList<MoonWithNulls>?
    ){
        data class MoonWithNulls(
            val name:String?,
            val founder:String?,
        )
    }
}


data class DataWithDefaults(
    val requestedAt:Date = Date(),
    val planets: ArrayList<PlanetsWithDefaults> = arrayListOf()
){
    data class PlanetsWithDefaults(
        val name:String = "PLANET_NAME",
        val founder:String? = null,
        val population:Long = 0,
        val distFromSun:Double = 0.0,
        val moons:ArrayList<MoonWithDefaults> = arrayListOf()
    ){
        data class MoonWithDefaults(
            val name:String = "MOON_NAME",
            val founder:String = "MOON_FOUNDER",
        )
    }
}


