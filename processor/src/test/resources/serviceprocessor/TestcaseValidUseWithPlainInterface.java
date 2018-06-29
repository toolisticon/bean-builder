import io.toolisticon.beanbuilder.api.Service;
import io.toolisticon.beanbuilder.processor.serviceprocessortest.TestSpiInterface;

@Service(TestSpiInterface.class)
public class TestcaseValidUseWithPlainInterface implements TestSpiInterface {
    public String doSomething() {
        return "";
    }
}