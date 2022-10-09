package test_results_moshi

import com.squareup.moshi.*
import test_results_moshi.UseDefaultIfNullFactory.UseDefaultIfNull
import java.util.*

@JsonClass(generateAdapter = true)
data class ApiDataNullable(
    @Json(name = "requested_at") val requestedAt:Date?,
    @Json(name = "planets") val planets: List<PlanetNullable>?
)
@JsonClass(generateAdapter = true)
data class PlanetNullable(
    @Json(name = "name")                val name:String?,
    @Json(name = "found_by")            val founder:String?,
    @Json(name = "population")          val population:Long?,
    @Json(name = "distnace_from_sun")   val distFromSun:Double?,
    @Json(name = "moons")               val moons:List<MoonNullable>?
)

@JsonClass(generateAdapter = true)
data class MoonNullable(
    @Json(name = "name")            val name:String?,
    @Json(name = "found_by")        val founder:String?,
)

//----------------------------------------------------------------

@UseDefaultIfNull
@JsonClass(generateAdapter = true)
data class ApiDataFilled(
    @Json(name = "requested_at") val requestedAt:Date = Date(),
    @Json(name = "planets")      val planets: List<PlanetFilled> = listOf()
)

@UseDefaultIfNull
@JsonClass(generateAdapter = true)
data class PlanetFilled(
    @Json(name = "name")                val name:String = "PLANET_NAME",
    @Json(name = "found_by")            val founder:String? = null,
    @Json(name = "population")          val population:Long = 42,
    @Json(name = "distnace_from_sun")   val distFromSun:Double = 42.42,
    @Json(name = "moons")               val moons:List<MoonFilled> = listOf()
)

@UseDefaultIfNull
@JsonClass(generateAdapter = true)
data class MoonFilled(
    @Json(name = "name")        val name:String = "MOON_NAME",
    @Json(name = "found_by")    val founder:String = "MOON_FOUNDER",
)

//----------------------------------------------------------------

object MyModelsMOSHI{
    fun printNullable(apiData: ApiDataNullable?) {
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
    fun printDefault(apiData: ApiDataFilled?){
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