package test_results_serialization


import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json



object MySerializeParser {
    fun getSerielizer(): Json {
            return Json{
                isLenient = true          // to not give error when strings keys/values are quoted with '' instead of  ""
                ignoreUnknownKeys = true  // to not give error when new keys are added or old keys are removed
                coerceInputValues =true   // to not give error when a key is absent and instead use default value if available or set as null

                @OptIn(ExperimentalSerializationApi::class)
                explicitNulls = false    // to not give error when a key is null and instead use default value if available or set as null

            }
    }
    fun getInstanceNullified(str:String): ApiDataNullable {
        return getSerielizer().decodeFromString(str)
    }
    fun getInstanceFilled(str:String): ApiDataFilled {
        return getSerielizer().decodeFromString(str)
    }
    inline fun <reified T> getInstance(str:String):T{
        return getSerielizer().decodeFromString(str)
    }

    fun toJsonString(obj:ApiDataNullable):String{
        return getSerielizer().encodeToString(obj)
    }
    fun toJsonString(obj:ApiDataFilled):String{
        return getSerielizer().encodeToString(obj)
    }

    inline fun <reified T : Any> toJsonStringSpecific(obj: T):String{
        return getSerielizer().encodeToString(obj)
    }

}