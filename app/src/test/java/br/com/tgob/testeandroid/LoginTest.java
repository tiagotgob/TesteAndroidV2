    package br.com.tgob.testeandroid;

    import org.junit.Test;

    import br.com.tgob.testeandroid.helper.CpfValidator;
    import br.com.tgob.testeandroid.helper.Validator;

    import static org.junit.Assert.*;

    /**
     * Example local unit test, which will execute on the development machine (host).
     *
     * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
     */
    public class LoginTest {
        @Test
        public void isValidPassword(){
            Validator validator = new Validator();
            assertTrue(validator.validatePassword("A@aa2aaaaaa"));
        }

        @Test
        public void isInvalidPassword(){
            Validator validator = new Validator();
            assertFalse(validator.validatePassword("A@2"));
        }

        @Test
        public void isValidEmail(){
            Validator validator = new Validator();
            assertTrue(validator.isValidEmail("tiagomoraes@hotmail.com"));
        }

        @Test
        public void isInvalidEmail(){
            Validator validator = new Validator();
            assertFalse(validator.isValidEmail("tiagomoraesadasdsotmail.com"));
        }

        @Test
        public void isValidCpf(){
            CpfValidator cpfValidator = new CpfValidator();
            assertTrue(cpfValidator.isCPF("33919227883"));
        }

        @Test
        public void isNotValidCpf(){
            CpfValidator cpfValidator = new CpfValidator();
            assertFalse(cpfValidator.isCPF("4444444"));
        }
    }