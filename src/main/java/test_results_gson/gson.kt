/*
 * This test will check:
 * 1. whether parser is able to set null when a model has no defaults for missing/null keys
 * 2. whether parser is able to set default value when a model has  defaults set for missing/null keys
 * 3. whether long and double are ideal for parsing long numbers
 * 4. whether dates can be parsed,
 * 5. scenario 1-4 , for java
 *
 * This test will not check:
 * 1. scenario where a model with some public variables have defaults but not all, this is somewhat an anomaly and is
 *    not an ideal model behavior for the parsers
 *
 * */

fun main(args: Array<String>) {
    println("Infinity".toDouble()+5)
}