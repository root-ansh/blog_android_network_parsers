package test_results_serialization
import input_data.MyInputs


/* KOTLIN RESULTS

 * case 1 : whether parser is able to set null when a model has no defaults for (a)missing , (b) null keys
 * result : (a)PARTIAL_PASS* (b)PARTIAL_PASS ,using custom flags in parser
 *
 * 2. whether parser is able to set default value when a model has  defaults set for (a) missing , (b) null keys
 * result : (a)PARTIAL_PASS* (b)PARTIAL_PASS ,using custom flags in parser
 *
 * 3. whether we are able to create a generic parser function that only takes model type, string as input
 * result : PASS
 *
 * 4. whether long and double are ideal for parsing long numbers
 * result : PARTIAL_PASS* : max length for long = 999_999_999_999_999_999 ~ 900K trillion
 *
 * 5. whether dates can be parsed,
 * result : PASS, using custom parser
 *
 */



fun main(args: Array<String>) {
   val inputs = MyInputs.i1

   val apiData = MySerializeParser.getInstance<ApiDataNullable>(inputs)
   MyModelsSERIALIZER.printNullable(apiData)

   val apiDataDefaults = MySerializeParser.getInstance<ApiDataFilled>(inputs)
   MyModelsSERIALIZER.printDefault(apiDataDefaults)


   println("WITH NULLS >> reverse string = "+ MySerializeParser.toJsonStringSpecific(apiData))
   println("WITH DEFAULTS >> reverse string = "+ MySerializeParser.toJsonStringSpecific(apiDataDefaults))


}