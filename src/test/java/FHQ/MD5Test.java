package FHQ;

import FHQ.Utils.MD5Utils;
import org.junit.Test;

public class MD5Test {

    @Test
    public void test() {
        String s = MD5Utils.toMD5("helloworld");
        System.out.println(s);
    }
}
