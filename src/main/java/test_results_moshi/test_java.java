package test_results_moshi;

import input_data.MyInputs;


public class test_java {


    public static void main(String[] args) {
        String jsonStr = MyInputs.INSTANCE.getI1();

        ApiDataNullable apiData = MoshiParser.INSTANCE.getInstanceNulls(jsonStr);
        MyModelsMOSHI.INSTANCE.printNullable(apiData);

        ApiDataFilled apiData2 = MoshiParser.INSTANCE.getInstanceDefaults(jsonStr);
        MyModelsMOSHI.INSTANCE.printDefault(apiData2);



        System.out.println("WITH NULLS >> reverse string = "+MoshiParser.INSTANCE.instanceToJsonNulls(apiData));
        System.out.println("WITH DEFAULSTS >> reverse string = "+MoshiParser.INSTANCE.instanceToJsonDefs(apiData2));

    }
}
