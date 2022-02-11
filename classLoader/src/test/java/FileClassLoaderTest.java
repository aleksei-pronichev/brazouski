import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.Test;

public class FileClassLoaderTest {

    @Test
    public void classLoaderShouldSuccessfullyReadCLassFile() {
        FileClassLoader classLoader = new FileClassLoader();
        try {
            classLoader.findClass("TestClassUniqueName");
        } catch (Exception e) {
            Assert.fail();
        }
        try {
            Class<?> classWithUniqueName = Class.forName("TestClassUniqueName");
            Object object = classWithUniqueName.getDeclaredConstructor().newInstance();
            Method methodOne = classWithUniqueName.getMethod("methodOne", String.class, int.class);
            Method methodTwo = classWithUniqueName.getMethod("methodTwo", String.class);
            methodOne.invoke(object, "text for methodOne", 5);
            methodTwo.invoke(object, "text for methodTwo");
        } catch (ReflectiveOperationException e) {
            Assert.fail("Class not found");
        }
    }

    @Test(expected = ClassFormatError.class)
    public void classLoaderShouldThrowIOExceptionCauseClassFileIsDamaged() {
        FileClassLoader classLoader = new FileClassLoader();
        try {
            classLoader.findClass("DamagedTestClass");
        } catch (ClassNotFoundException ignored) {
        }
    }
}
