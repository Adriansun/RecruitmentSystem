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
 * Class checks if ssn contains the right numbers.
 *
 */
@Constraint(validatedBy = ValidSSN.SSNValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSSN {
    
    /**
     * Error message if the ssn is in the wrong format.
     *
     * @return message message
    */
    String message() default "{invalidSSN}";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    /**
     * Validation class for ssn.
     */
    class SSNValidator implements ConstraintValidator<ValidSSN, String> 
    {
        /**
         * Start validation.
         * 
         * @param constraintAnnotation contraints
         */
        @Override
        public void initialize(ValidSSN constraintAnnotation) {
        }

        /**
        * If ssn is in the correct format then true.
        * 
        * @param value of ssn
        * @param context context
        * @return boolean if ssn is ok or not
        */
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) 
        {
            if (isEmpty(value, context)) {
                return false;
            }
          
            return value.matches("([0-9]{6})-([0-9]{4})") || 
                    value.matches("([0-9]{8})-([0-9]{4})") ||
                    value.matches("([0-9]{3})-([0-9]{2})-([0-9]{2})");
        }

        /**
        * Check if ssn has been written in or not.
        *
        * @param value of ssn
        * @param context context
        * @return boolean if ssn been written in or not
        */
        private boolean isEmpty(String value, ConstraintValidatorContext context) {
            if (value.length() == 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{noSSN}").addConstraintViolation();
                return true;
            }
            return false;
        }
    }
}