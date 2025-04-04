package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;



class ValidationTest {

    @Test
    void testValidate() {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        assertThat(result1).isEqualTo(expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        assertThat(result2).isEqualTo(expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        assertThat(result3).isEqualTo(expected3);
    }

    // BEGIN
 @Test
    void testValidateWithNotNullViolation() {
        Address address = new Address(null, "London", "1-st street", "7", "2");

        List<String> result = Validator.validate(address);

        assertThat(result).containsExactly("country");
    }

    @Test
    void testValidateWithMultipleViolations() {
        Address address = new Address("England", null, null, "7", "2");

        List<String> result = Validator.validate(address);

        assertThat(result).containsExactlyInAnyOrder("city", "street");
    }

    @Test
    void testAdvancedValidateWithLengthAndNullErrors() {
        Address address = new Address("USA", "Texas", null, "7", "2");

        Map<String, List<String>> result = Validator.advancedValidate(address);

        assertThat(result.get("country")).containsExactly("length less than 4");
        assertThat(result.get("street")).containsExactly("can not be null");
    }

    @Test
    void testAdvancedValidateWithNoErrors() {
        Address address = new Address("France", "Paris", "Rue de Rivoli", "10", null);

        Map<String, List<String>> result = Validator.advancedValidate(address);

        assertThat(result).isEmpty();
    }    
    // END
}
