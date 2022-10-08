
import input_data.DataWithDefaults_M
import input_data.DataWithNulls_M
import input_data.MyInputs
import input_data.MyMoshi


/*
 * This test will check:
 * 1. whether parser is able to set null when a model has no defaults for missing/null keys
 * 2. whether parser is able to set default value when a model has  defaults set for missing/null keys
 * 3. whether long and double are ideal for parsing long numbers
 * 4. whether dates can be parsed,
 * 5. scenario 1-4 , for java
 *

 * */


/* RESULTS::::::
 * case 1 : whether parser is able to set null when a model has no defaults for missing/null keys
 * result : PASS
 *
 * 2. whether parser is able to set default value when a model has  defaults set for missing/null keys
 * result : FAIL
 *
 *
 * 3. whether long and double are ideal for parsing long numbers
 * result : PARTIAL_PASS* : max length for long = 999_999_999_999_999_999 ~ 900K trillion
 *
 * 4. whether dates can be parsed,
 * result : PASS, using custom parser
 *
 * 5. scenario 1-4 , for java
 * result : PASS, 1,3,4 worked for java
 *
 * This test will not check:
 * 1. scenario where a model with some public variables have defaults but not all, this is somewhat an anomaly and is
 *    not an ideal model behavior for the parsers
 * */

fun main(args: Array<String>) {
   val data = MyInputs.i1

   val datawithnullsM = MyMoshi.getInstanceNulls(data)
   printer("datawithnullsM",datawithnullsM)

//   val dataWithDefaults_M = MyMoshi.getInstanceDefaults(data)
//   printer("dataWithDefaults_M",dataWithDefaults_M)


}

@Suppress("ScopeFunctionConversion", "ComplexRedundantLet", "DuplicatedCode")
fun printer(key:String, data:Any?){
   println("$key====================================")
   println("data= ${data?.hashCode() ?: "null"}")
   if(data is DataWithNulls_M ){
      data.requestedAt.let { println("requestedAt= $it") }
      data.planets.let { println("planets:${it?.size?:"null"}") }
      data.planets?.forEach {item ->
         item.name.let { println("\t name=$it") }
         item.founder.let { println("\t founder=$it") }
         item.population.let { println("\t population=$it") }
         item.distFromSun.let { println("\t distFromSun=$it") }
         item.moons.let { println("\t moons:${it?.size?:"null"}") }
         item.moons?.forEach {moon->
            moon.name.let { println("\t\t name=$it") }
            moon.founder.let { println("\t\t founder=$it") }
         }
         println("---- ---- ---- ---- ")
      }
   }

   if(data is DataWithDefaults_M ){
      //data.requestedAt.let { println("date= $it") }
      data.planets.let { println("planets:${it?.size?:"null"}") }
      data.planets?.forEach {item ->
         item.name.let { println("\t name=$it") }
         item.founder.let { println("\t founder=$it") }
         item.population.let { println("\t population=$it") }
         item.distFromSun.let { println("\t distFromSun=$it") }
         item.moons.let { println("\t moons:${it?.size?:"null"}") }
         item.moons?.forEach {moon->
            moon.name.let { println("\t\t name=$it") }
            moon.founder.let { println("\t\t founder=$it") }
         }
         println("---- ---- ---- ---- ")
      }
   }
}