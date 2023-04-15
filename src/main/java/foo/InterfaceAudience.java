package foo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@InterfaceAudience.Public
public class InterfaceAudience {
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Public {}
}