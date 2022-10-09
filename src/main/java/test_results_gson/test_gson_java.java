package test_results_gson;

import input_data.MyInputs;


/* JAVA RESULTS
 * 1. scenario 1-5 , for java
 * result : PASS, 1,2,4,5 worked for java .  generic parser function not accessible via java
 */
public class test_gson_java {


    public static void main(String[] args) {
        String jsonStr = MyInputs.INSTANCE.getI1();

        ApiDataNullable apiData = MyGsonParser.INSTANCE.getInstanceNullified(jsonStr);
        MyModelsGSON.INSTANCE.printObj(apiData);

        ApiDataFilled apiData2 = MyGsonParser.INSTANCE.getInstanceFilled(jsonStr);
        MyModelsGSON.INSTANCE.printObj(apiData2);



        System.out.println("WITH NULLS >> reverse string = "+MyGsonParser.INSTANCE.toJsonString(apiData));
        System.out.println("WITH DEFAULSTS >> reverse string = "+MyGsonParser.INSTANCE.toJsonString(apiData2));

    }
}
