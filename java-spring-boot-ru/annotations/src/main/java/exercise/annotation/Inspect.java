package exercise.annotation;

// BEGIN
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// Аннотация будет видна во время выполнения
@Retention(RetentionPolicy.RUNTIME)
// Можно применять только к методам
@Target(ElementType.METHOD)
public @interface Inspect {
}
// END
