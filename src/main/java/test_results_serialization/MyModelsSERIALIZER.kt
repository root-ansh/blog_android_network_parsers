package test_results_serialization

import kotlinx.serialization.KSerializer
import java.util.Date
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.SimpleDateFormat

object DateSerializer : KSerializer<Date> {
    override val descriptor = PrimitiveSerialDescriptor("Date", PrimitiveKind.STRING)
    override fun serialize(encoder: Encoder, value: Date) {
        encoder.encodeString(value.toString())
    }
    override fun deserialize(decoder: Decoder): Date {
        val timestamp = decoder.decodeString()
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        return try {
            sdf.parse(timestamp)
        }
        catch (e:Throwable){
            println("some error happened during parsing")
            System.err.println(e)
            Date()
        }
    }
}


@Serializable
data class ApiDataNullable(
    @SerialName("requested_at") @Serializable(DateSerializer::class) val requestedAt:Date?,
    @SerialName("planets") val planets: List<PlanetNullable>?
)

@Serializable
data class PlanetNullable(
    @SerialName("name")                val name:String?,
    @SerialName("found_by")            val founder:String?,
    @SerialName("population")          val population:Long?,
    @SerialName("distnace_from_sun")   val distFromSun:Double?,
    @SerialName("moons")               val moons:List<MoonNullable>?
)

@Serializable
data class MoonNullable(
    @SerialName("name")            val name:String?,
    @SerialName("found_by")        val founder:String?,
)

//----------------------------------------------------------------


@Serializable
data class ApiDataFilled(
    @SerialName("requested_at") @Serializable(DateSerializer::class) val requestedAt:Date = Date(),
    @SerialName("planets")      val planets: List<PlanetFilled> = listOf()
)

@Serializable
data class PlanetFilled(
    @SerialName("name")                val name:String = "PLANET_NAME",
    @SerialName("found_by")            val founder:String? = null,
    @SerialName("population")          val population:Long = 42,
    @SerialName("distnace_from_sun")   val distFromSun:Double = 42.42,
    @SerialName("moons")               val moons:List<MoonFilled> = listOf()
)

@Serializable
data class MoonFilled(
    @SerialName("name")        val name:String = "MOON_NAME",
    @SerialName("found_by")    val founder:String = "MOON_FOUNDER",
)
object MyModelsSERIALIZER{
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

