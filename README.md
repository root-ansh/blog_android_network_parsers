# Parsers in Android

Note: This is NOT an Android Studio project. open it in intellij

## About

This is a small repo/blog about  how 3 parsers : `Gson`, `Moshi` and `kotlinx-serializer` can be used  convert a JSON string to object and vice versa, based on specific conditions/tests

### About Parsers

<details open>
<summary>Back Story</summary>

- We know that JSON is one of the most common ways of communicating among clients and server on the internet. JSON is basically just a blob of human readable data that gets converted to 1s and 0s and transmitted across the various network components as a streamable format , i,e open stream of bits.

- **However,** to be converted into a meaningful format and to be consumed by a client side software, this blob of data needs to be parsed and converted into a format that the client side software can understand.

- In our case, the client side softwares is **Android** and the language it understands is **Java/Kotlin** .

</details>

*Thus, **Parsers** in Android are basically the softwares/libraries/pieces of code that will read the blobs of data received via the interned, and **convert it into instances of java/kotlin classes(data models) AND Vice Versa***

<details open>
<summary>More Info</summary>

There are various properties associated with a parser:
- A parser should be fast and consume less memory while encoding/decoding the data
- A parser should add small footprint to the main code ( i.e consume less space as a dependency) 
- A parser should be able to convert data into primitive(`int` ,`boolean`, `string` etc) and non primitive(`Date` , `Student`, `School` etc) formats.
- Most importantly, **A parser should be able to deal with the "Loose" nature of JSON format and able to parse it into the "Strict" boundaries of Java/Kotlin Classes** : What do I mean by this? Well, JSON and java define the definitions of various data types differently, so a parser should be able to handle them correctly. for eg, the key in `"key" : 23` in json is not guarenteed to receive  an integer always , and might get say `26.5` in future, therefore it is unwise to parse the `key` as an integer . read more of the quirks in JSON section

</details>


<details open>
<summary> <h3> About JSON </h3> </summary>

The various types of data that a json can consist are : 

### Primitive  types : `String`,`Number`,`Boolean`, `null`

- `null` : Simply meaning that the key is undefined .
  - it is upto parsers to define whether they will be considering key defined as null (i.e `"key": null`)  and key not present as both null or different
  
- `String` : Plain old strings. End delimeters and special characters are UTF encoded. can be millions of gb in size
- `Boolean` : Plain old `true` and `false` . supported by all languages
- `Number` :
  - Consists of  **Decimal numbers**(25/4 or -6.25) and **Integers**(-3,-2,0,1,2,..) . Irrational numbers(pi aka 3.1427... or e) cannot be represented in json . 
  - there is no size limit of a JSON number . A number of 1 followed by a million zeroes is also a valid JSON Number
  
  - Java does not have a JSON like primitve number class, but rather a list of number classes based on the amount/type of data they might store :
    - Byte : for storing exact Integer numbers  in range of  ± 127 
    - Int : for storing  exact Integer numbers  in range of approx ± 200 crore ( -2<sup>31</sup> to  2<sup>31</sup> -1)  
    - Long : for storing exact Integer numbers  in a very large range  (-2<sup>63</sup> to 2<sup>63</sup> -1 )
    - Float : for storing less precise Integer and Decimal numbers  in a  large range (-2<sup>34</sup> to 2<sup>34</sup> -1 )
    - Double : for storing less precise Integer and Decimal numbers  in a  very very large range (-2<sup>308</sup> to 2<sup>308</sup> -1 )
    
  - In case of java decimal/float types, parsing a json value of `999999` to float or decimal may produce the output as `999987.321999`, thus they are unreliable for parsing data.
  - However since decimal/float have the highest storage capacity, it may seem that double is the way to go with json as js can generate any number, but long or int should also be considered if they don't have to be in decimal or could be modified via a math operation. size of is approx 9 petabytes = total millis in 0.95 light years . so its long enough for a lot of data

### Collection types : array, object:
- array : can consist of other primitive types, object , be null or be  empty

- object:
  - json object is  a unordered collection of key value pairs . the keys are always string and expected to be  unique . if keys are repeated, the value might get overridden or parsed as range, depending upon the parser
  - common java classes that can be used to map it: hashtable, hashmap. as these maps use hashing algos for creating object, hash-dos attack is an important concern


</details>


# Testing the Various Parsers in Android 

So this repo is created to test some common scenarios that may exist with a json data and how parsers are supposed to deal with them. We imagine the data to be one of the samples from NASA data, which tells about some planets and their moons in a solar system
 
<details open>
  <summary><h3>Input Data</h3></summary>
  
  ```json
   {
         "requested_at": "2020-04-03T09:44:57",
         "planets": [
           {
             "name": "venus",
             "found_by": "jagga & singh",
             "population": null,
             "moons": []
           },
           {
             "name": "earth",
             "population": 999999999999999999,
             "distnace_from_sun": 999888777666555444333222111.32,
             "moons": [
               {
                 "name": "moon",
                 "found_by": "neil armstrong"
               }
             ]
           },
           {
             "name": "mars",
             "found_by": "elon musk",
             "population": 1,
             "distnace_from_sun": 999888777666555444333222111000.32,
             "moons": [
               {"name": "tyler"},
               {
                 "name": "jackson",
                 "found_by": "jackson hanama"
               }
             ]
           }
         ]
       }
  ```
</details>


### Test Cases

The Input block is a simple JSON file that covers a lot of testcases: 

1. **Parsing Non Primitive Types** : the key `requested_at` is of type string but is actually a UTF based timestamp. so the parser is supposed to convert this into `Date` Object Type. 
2. **Special characters**  : The parser is supposed to convert strings like `"jagga & singh"` as it is and not convert special character to theri UTF value
3. **Absent Keys Scenario (IMP)** : Some keys like `"distnace_from_sun"` and `"name"` are missing . The parser should be capable of  setting the value of key as null or default value based on the type defined by the key itself
4. **Null Keys Scenario  (IMP)** : Some keys like `"population"` are having their value as `null` in some cases. again, The parser should be capable of  setting the value of key as null or default value based on the type defined by the key itself
5. **Long Integers and Decimal numbers** : Some values for keys like `"distnace_from_sun"` and `"population"` have a very large value . The parser should be able to parse them correctly

In addition to above, There are some code level expectations from the parser libraries too :

6. The parser should be able to provide a generic function to be used for any model, so as to not write `parser.toModel(jsonString,ModelClass::java)` everytime. makes it easy to use at scale.  

7. The parser function should be able to get called from both java and kotlin code withou causing any disruptions


We will be creating instances of parsers and validating these cases for  all of them : GSON, Moshi an d Kotlinx Serialization

---

### Results

**GSON**
- adding dependency in  module : [here](https://github.com/root-ansh/testing_parsers/blob/main/build.gradle.kts)  | adding dependency in root project : not needed
- creating parser : [here](https://github.com/root-ansh/testing_parsers/blob/main/src/main/java/test_results_gson/MyGsonParser.kt) 
- test cases and results : [kotlin](https://github.com/root-ansh/testing_parsers/blob/main/src/main/java/test_results_gson/test_gson.kt)  and [java](https://github.com/root-ansh/testing_parsers/blob/main/src/main/java/test_results_gson/test_gson_java.java) 

**Moshi**
- adding dependency in  module : [here](https://github.com/root-ansh/testing_parsers/blob/main/build.gradle.kts)  | adding dependency in root project : not needed
- creating parser : [here](https://github.com/root-ansh/testing_parsers/blob/main/src/main/java/test_results_moshi/MyMoshiParser.kt) 
- test cases and results : [kotlin](https://github.com/root-ansh/testing_parsers/blob/main/src/main/java/test_results_moshi/test.kt)  and [java](https://github.com/root-ansh/testing_parsers/blob/main/src/main/java/test_results_moshi/test_java.java) 

**Koltinx Serialisation**
- adding dependency in  module : [here](https://github.com/root-ansh/testing_parsers/blob/main/build.gradle.kts)  | adding dependency in root project : [here](https://github.com/root-ansh/testing_parsers/blob/main/build.gradle.kts)
- creating parser : [here](https://github.com/root-ansh/testing_parsers/blob/main/src/main/java/test_results_serialization/MySerializeParser.kt) 
- test cases and results : [kotlin](https://github.com/root-ansh/testing_parsers/blob/main/src/main/java/test_results_serialization/test.kt)  and [java](https://github.com/root-ansh/testing_parsers/blob/main/src/main/java/test_results_serialization/test_java.java) 


<details open>
<summary><h3>Resources</h3></summary>

- heavily inspired by this awesome talk by @jessiwilson  and its notes : https://speakerdeck.com/swankjesse/json-explained-chicago-roboto-2019
- good for understanding moshi custom adapters : https://bladecoder.medium.com/advanced-json-parsing-techniques-using-moshi-and-kotlin-daf56a7b963d
- good for understanding moshi codegen : https://www.zacsweers.dev/exploring-moshis-kotlin-code-gen/
</details>





