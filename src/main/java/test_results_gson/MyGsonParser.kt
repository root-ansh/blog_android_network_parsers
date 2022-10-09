package test_results_gson

import com.google.gson.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date

object MyGsonParser {
    fun getGson():Gson{
        val dataAdapter = JsonDeserializer { json: JsonElement, _: Type?, _: JsonDeserializationContext? ->
            val timestamp = json.asJsonPrimitive.asString
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            try {
                sdf.parse(timestamp)
            }
            catch (e:Throwable){
                println("some error happened during parsing")
                System.err.println(e)
                Date()
            }

        }
        return GsonBuilder().registerTypeAdapter(Date::class.java,dataAdapter).serializeNulls().disableHtmlEscaping().create()
    }
    fun getInstanceNullified(str:String):ApiDataNullable{
        return getGson().fromJson(str,ApiDataNullable::class.java)
    }
    fun getInstanceFilled(str:String):ApiDataFilled{
        return getGson().fromJson(str,ApiDataFilled::class.java)
    }
    inline fun <reified T> getInstance(str:String):T{
        return getGson().fromJson(str,T::class.java)
    }

    fun toJsonString(obj:Any):String{
        return getGson().toJson(obj)
    }

    inline fun <reified T> toJsonStringSpecific(obj: Any):String{
        return getGson().toJson(obj,T::class.java)
    }

}