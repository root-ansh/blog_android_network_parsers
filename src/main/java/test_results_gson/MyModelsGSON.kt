package test_results_gson

import com.google.gson.annotations.SerializedName
import java.util.*

data class ApiDataNullable(
    @SerializedName( "requested_at") val requestedAt:Date?,
    @SerializedName( "planets") val planets: List<PlanetNullable>?
)

data class PlanetNullable(
    @SerializedName( "name")                val name:String?,
    @SerializedName( "found_by")            val founder:String?,
    @SerializedName( "population")          val population:Long?,
    @SerializedName( "distnace_from_sun")   val distFromSun:Double?,
    @SerializedName( "moons")               val moons:List<MoonNullable>?
)

data class MoonNullable(
    @SerializedName( "name")            val name:String?,
    @SerializedName( "found_by")        val founder:String?,
)

//----------------------------------------------------------------

data class ApiDataFilled(
    @SerializedName( "requested_at") val requestedAt:Date = Date(),
    @SerializedName( "planets")      val planets: List<PlanetFilled> = listOf()
)

data class PlanetFilled(
    @SerializedName( "name")                val name:String = "PLANET_NAME",
    @SerializedName( "found_by")            val founder:String? = null,
    @SerializedName( "population")          val population:Long = 42,
    @SerializedName( "distnace_from_sun")   val distFromSun:Double = 42.42,
    @SerializedName( "moons")               val moons:List<MoonFilled> = listOf()
)

data class MoonFilled(
    @SerializedName( "name")        val name:String = "MOON_NAME",
    @SerializedName( "found_by")    val founder:String = "MOON_FOUNDER",
)

//----------------------------------------------------------------

object MyModelsGSON{
    fun printObj(apiData: ApiDataNullable?) {
        apiData.let { data ->

            val builder = java.lang.StringBuilder()
            builder
                .append("\n==== WITH NULLS ================================")
                .append("\ndata= id_${data?.hashCode() ?: "null"}")
            if(data==null) return@let

            builder
                .append("\nrequestedAt= ${data.requestedAt}")
                .append("\nplanets:${data.planets?.size ?: "null"}")

            if(data.planets ==null) return@let

            data.planets.forEach { item ->
                builder
                    .append("\n\t name=${item.name}")
                    .append("\n\t founder=${item.founder}")
                    .append("\n\t population=${item.population}")
                    .append("\n\t distFromSun=${item.distFromSun}")
                    .append("\n\t moons:${item.moons?.size ?: "null"}")
                item.moons?.forEach {  builder.append("\n\t\t >> name=${it.name} |founder=${it.founder}") }
                builder.append("\n---- ---- ---- ---- ")
            }
            println(builder.toString())
        }
    }
    fun printObj(apiData: ApiDataFilled?){
        apiData.let { data ->
            val builder = java.lang.StringBuilder()
            builder
                .append("\n==== WITH DEFAULTS ================================")
                .append("\ndata= id_${data?.hashCode() ?: "null"}")
            if(data==null) return@let

            builder
                .append("\nrequestedAt= ${data.requestedAt}")
                .append("\nplanets:${data.planets?.size ?: "null"}")

            if(data.planets ==null) return@let

            data.planets.forEach { item ->
                builder
                    .append("\n\t name=${item.name}")
                    .append("\n\t founder=${item.founder}")
                    .append("\n\t population=${item.population}")
                    .append("\n\t distFromSun=${item.distFromSun}")
                    .append("\n\t moons:${item.moons?.size ?: "null"}")
                item.moons?.forEach {  builder.append("\n\t\t >> name=${it.name} |founder=${it.founder}") }
                builder.append("\n---- ---- ---- ---- ")
            }
            println(builder.toString())


        }
    }

}