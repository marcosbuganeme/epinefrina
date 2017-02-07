package encaixeme.persistencia.transacional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Anotação de marcação [ QUALIFIER ] para métodos transacionais
 * 
 * @author Marcos Olavo S. Buganeme
 */

@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Transacao {
}