package view.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * Class checks if the e-mail has been written in and is correct.
 */
@Constraint(validatedBy = ValidEmail.EmailValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    
    /**
     * Error message if the e-mail is in the wrong format.
     *
     * @return boolean if right format or not
     */
    String message() default "{invalidEmail}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
    
    /**
     *Validation class for e-mail.
     */
    class EmailValidator implements ConstraintValidator<ValidEmail, String> 
    {
        /**
         * Start validation of e-mail.
         * 
         * @param constraintAnnotation constraints
         */
        @Override
        public void initialize(ValidEmail constraintAnnotation) {
        }
        
        /**
         * Checks if the e-mail is in the right format.
         *
         * @param value the e-mail
         * @param context context
         * @return boolean true if e-mail is in the correct format
         */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) 
        {
            if(isEmpty(value, context)) {
                return false;
            }
            
            return value.matches("([a-z0-9]+?)@([a-z0-9]+?)\\.([a-z0-9]+?)") || 
                   value.matches("([a-z0-9]+?)\\.([a-z0-9]+?)@([a-z0-9]+?)\\.([a-z0-9]+?)");
        }
        
        /**
         * Checks if the e-mail has been written in or not.
         *
         * @param value the e-mail
         * @param context context
         * @return boolean true if e-mail has been written in
         */
        private boolean isEmpty(String value, ConstraintValidatorContext context) {
            if (value.length() == 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{noEmail}").addConstraintViolation();
                return true;
            }
            return false;
        }
    }
}
