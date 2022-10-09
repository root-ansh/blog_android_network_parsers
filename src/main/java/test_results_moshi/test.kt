package test_results_moshi
import input_data.MyInputs


/* KOTLIN RESULTS
 * case 1 : whether parser is able to set null when a model has no defaults for (a)missing , (b) null keys
 * result : (a)PASS (b)PASS
 *
 * 2. whether parser is able to set default value when a model has  defaults set for (a) missing , (b) null keys
 * result : (a)PASS (b)PASS , using null nulladapter and null handler annotation
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

/* JAVA RESULTS
 * 1. scenario 1-5 , for java
 * result : PASS, 1,2,4,5 worked for java .  generic parser function not accessible via java
 *
 */


fun main(args: Array<String>) {
   val inputs = MyInputs.i1

   val apiData = MoshiParser.getInstanceGeneric<ApiDataNullable>(inputs)
   MyModelsMOSHI.printNullable(apiData)

   val apiDataDefaults = MoshiParser.getInstanceGeneric<ApiDataFilled>(inputs)
   MyModelsMOSHI.printDefault(apiDataDefaults)


   println("WITH NULLS >> reverse string = "+ MoshiParser.instanceToJsonNulls(apiData!!))
   println("WITH DEFAULTS >> reverse string = "+ MoshiParser.instanceToJsonDefs(apiDataDefaults!!))


}