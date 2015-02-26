package dk.cphbusiness.hello;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;

public class ProposalsStubTest {
    private IProposal props;
    
    @Before
    public void setUp(){
        props = new ProposalStub();
        ArrayList t1 = new ArrayList();
        ArrayList t2 = new ArrayList();
        t1.add("Peter");
        t1.add("Thomas");
        t2.add("Torben");
        t2.add("Anders");
        
        props.addProposal("Android", "Make more apps", t1);
        props.addProposal("C#", "Make good apps", t2);
    }
}
