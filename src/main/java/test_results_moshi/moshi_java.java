package test_results_moshi;

import input_data.DataWithNulls_M;
import input_data.MyInputs;
import input_data.MyMoshi;

public class moshi_java {


    public static void main(String[] args) {
        String data = MyInputs.INSTANCE.getI1();
        DataWithNulls_M obj = MyMoshi.INSTANCE.getInstanceNulls(data);

        System.out.println(obj);
    }
}
