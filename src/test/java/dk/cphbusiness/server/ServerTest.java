package dk.cphbusiness.server;

import dk.cphbusiness.exceptions.MinimumCharacterException;
import java.io.IOException;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class ServerTest {
//    Server server;

    @Before
    public void setUp() throws IOException, MinimumCharacterException {
        String[] args = new String[2];
        args[0] = "8090";
        args[1] = "localhost";
        Server.main(args);
    }
    
    @After
    public void tearDown(){
        Server.stop();
    }
    
    @Test
    public void testServer() throws IOException{
        assertThat(Server.ip, is("localhost"));
        assertThat(Server.port, is(8090));
    }

}
