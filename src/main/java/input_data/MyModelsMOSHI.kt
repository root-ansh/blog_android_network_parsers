package input_data

import com.squareup.moshi.*
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = true)
data class DataWithNulls_M(
    @Json(name = "requested_at") val requestedAt:Date?,
    @Json(name = "planets") val planets: List<PlanetsWithNulls_M>?
){
    @JsonClass(generateAdapter = true)
    data class PlanetsWithNulls_M(
        @Json(name = "name")                val name:String?,
        @Json(name = "found_by")            val founder:String?,
        @Json(name = "population")          val population:Long?,
        @Json(name = "distnace_from_sun")   val distFromSun:Double?,
        @Json(name = "moons")               val moons:List<MoonWithNulls_M>?
    ){
        @JsonClass(generateAdapter = true)
        data class MoonWithNulls_M(
            @Json(name = "name")            val name:String?,
            @Json(name = "found_by")        val founder:String?,
        )
    }
}

@JsonClass(generateAdapter = true)
data class DataWithDefaults_M(
    @Json(name = "requested_at") val requestedAt:Date = Date(),
    @Json(name = "planets")      val planets: List<PlanetsWithDefaults_M> = listOf()
){

    @JsonClass(generateAdapter = true)
    data class PlanetsWithDefaults_M(
        @Json(name = "name")                val name:String = "PLANET_NAME",
        @Json(name = "found_by")            val founder:String? = null,
        @Json(name = "population")          val population:Long = 0,
        @Json(name = "distnace_from_sun")   val distFromSun:Double = 0.0,
        @Json(name = "moons")               val moons:List<MoonWithDefaults_M> = listOf()
    ){
        @JsonClass(generateAdapter = true)
        data class MoonWithDefaults_M(
            @Json(name = "name")        val name:String = "MOON_NAME",
            @Json(name = "found_by")    val founder:String = "MOON_FOUNDER",
        )
    }
}

object DateAdapter {
    @ToJson fun toJson(date: Date): String {
        return date.toString()
    }
    @FromJson fun fromJson(timestamp: String): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return try {
            sdf.parse(timestamp)
        }
        catch (e:Throwable){
            println("some error happenned during parsing")
            System.err.println(e)
            Date()
        }

    }
}
object MyMoshi {
    fun getMoshi():Moshi{
        return Moshi.Builder().add(DateAdapter).build()
    }
    fun  getInstanceNulls(str:String):DataWithNulls_M?{
        val moshi = getMoshi()
        val adapter : JsonAdapter<DataWithNulls_M> = moshi.adapter(DataWithNulls_M::class.java)
        return adapter.fromJson(str)
    }
    fun  getInstanceDefaults(str:String):DataWithDefaults_M?{
        val moshi = getMoshi()

        return moshi.adapter(DataWithDefaults_M::class.java).fromJson(str)
    }

    inline fun <reified T> getInstanceGeneric(str:String): T?{
        return getMoshi().adapter(T::class.java).fromJson(str)
    }


    fun  instanceToJsonDefs(instance:DataWithDefaults_M):String{
        return getMoshi().adapter(DataWithDefaults_M::class.java).toJson(instance)
    }
    fun  instanceToJsonNulls(instance:DataWithNulls_M):String{
        return getMoshi().adapter(DataWithNulls_M::class.java).toJson(instance)
    }
    inline fun <reified T> instanceToJsonGeneric(ins:T):String{
        return getMoshi().adapter(T::class.java).toJson(ins)
    }

}
