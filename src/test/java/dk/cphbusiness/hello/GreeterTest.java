package dk.cphbusiness.hello;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

public class GreeterTest {
  
  @Test
  public void testSayHello() throws Exception {
    assertThat(Greeter.sayHello(), is("Hello World!"));
    }
  
  @Test
  public void testSayHelloKurt() throws Exception {
    assertThat(Greeter.sayHello("Kurt"), is("Hello Kurt!"));
    }
  
  }
