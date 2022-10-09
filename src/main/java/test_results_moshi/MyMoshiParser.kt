package test_results_moshi

import com.squareup.moshi.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

/* prerequisites:
 *      implementation("com.squareup.moshi:moshi:1.14.0")
 *      ksp("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
 */

/* This test will not check:
 * 1. scenario where a model with some public variables have defaults but not all, this is somewhat an anomaly and is
 *    not an ideal model behavior for the parsers
 * 2. scenario where a model is java class / non data class
 * */

/* RESULTS
 * Check associated test classes for results
 * */



class UseDefaultIfNullFactory : JsonAdapter.Factory {
/*
 * This factory basically 1) identifies  the models that are marked with @UseDefaultIfNull annotation
 */


    @Retention(AnnotationRetention.RUNTIME)
    @Target(AnnotationTarget.CLASS)
    annotation class UseDefaultIfNull


    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if (!Types.getRawType(type).isAnnotationPresent(UseDefaultIfNull::class.java)) {
            return null
        }

        val delegate = moshi.nextAdapter<Any>(this, type, annotations)

        return object : JsonAdapter<Any>() {
            override fun fromJson(reader: JsonReader): Any? {
                @Suppress("UNCHECKED_CAST")
                val blob = reader.readJsonValue() as Map<String, Any?>
                val noNulls = blob.filterValues { it != null }
                return delegate.fromJsonValue(noNulls)
            }

            override fun toJson(writer: JsonWriter, value: Any?) {
                return delegate.toJson(writer, value)
            }
        }
    }
}



object DateAdapter {
    @ToJson
    fun toJson(date: Date): String {
        return date.toString()
    }
    @FromJson
    fun fromJson(timestamp: String): Date {
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



object MyMoshiParser {
    fun getMoshi(): Moshi {
        return Moshi.Builder().add(UseDefaultIfNullFactory()).add(DateAdapter).build()
    }
    fun  getInstanceNullified(str:String): ApiDataNullable?{
        val moshi = getMoshi()
        val adapter : JsonAdapter<ApiDataNullable> = moshi.adapter(ApiDataNullable::class.java)
        return adapter.fromJson(str)
    }
    fun  getInstanceFilled(str:String): ApiDataFilled?{
        val moshi = getMoshi()

        return moshi.adapter(ApiDataFilled::class.java).fromJson(str)
    }

    inline fun <reified T> getInstanceGeneric(str:String): T?{
        return getMoshi().adapter(T::class.java).fromJson(str)
    }


    fun  instanceToJsonDefs(instance: ApiDataFilled):String{
        return getMoshi().adapter(ApiDataFilled::class.java).toJson(instance)
    }
    fun  instanceToJsonNulls(instance: ApiDataNullable):String{
        return getMoshi().adapter(ApiDataNullable::class.java).toJson(instance)
    }
    inline fun <reified T> instanceToJsonGeneric(ins:T):String{
        return getMoshi().adapter(T::class.java).toJson(ins)
    }

}