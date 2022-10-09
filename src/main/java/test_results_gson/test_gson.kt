package test_results_gson

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



fun main(args: Array<String>) {
   val inputs = MyInputs.i1

   val pojoNullified = MyGsonParser.getInstance<ApiDataNullable>(inputs)
   MyModelsGSON.printObj(pojoNullified)

   val pojoFilled = MyGsonParser.getInstance<ApiDataFilled>(inputs)
   MyModelsGSON.printObj(pojoFilled)


//   println("WITH NULLS >> reverse string = "+ MyGsonParser.toJsonString(pojoNullified))
   println("WITH NULLS ENHANCED >> reverse string = "+ MyGsonParser.toJsonStringSpecific<ApiDataNullable>(pojoNullified))

//   println("WITH FILLED >> reverse string = "+ MyGsonParser.toJsonString(pojoFilled))
   println("WITH FILLED ENHANCED >> reverse string = "+ MyGsonParser.toJsonStringSpecific<ApiDataFilled>(pojoFilled))



}