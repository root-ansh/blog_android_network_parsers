package test_results_moshi;

import input_data.MyInputs;



/* JAVA RESULTS
 * 1. scenario 1-5 , for java
 * result : PASS, 1,2,4,5 worked for java .  generic parser function not accessible via java
 */
public class test_java {


    public static void main(String[] args) {
        String jsonStr = MyInputs.INSTANCE.getI1();

        ApiDataNullable apiData = MyMoshiParser.INSTANCE.getInstanceNullified(jsonStr);
        MyModelsMOSHI.INSTANCE.printNullable(apiData);

        ApiDataFilled apiData2 = MyMoshiParser.INSTANCE.getInstanceFilled(jsonStr);
        MyModelsMOSHI.INSTANCE.printDefault(apiData2);



        System.out.println("WITH NULLS >> reverse string = "+ MyMoshiParser.INSTANCE.instanceToJsonNulls(apiData));
        System.out.println("WITH DEFAULSTS >> reverse string = "+ MyMoshiParser.INSTANCE.instanceToJsonDefs(apiData2));

    }
}
