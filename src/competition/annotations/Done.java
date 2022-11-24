package competition.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Denotes a correct solution to the problem
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Done {
    /**
     * An optional string denoting the original location the problem was found
     *
     * @return The URL to the problem
     */
    String url() default "No URL";
}
