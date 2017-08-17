import org.junit.Test;
import org.smart4j.framework.util.ClassUtil;

import java.util.Set;

public class TestDemo {
    @Test
    public void Test1()
    {
        Set<?> set= ClassUtil.getClassSet("org.apache.commons");
    }
}
