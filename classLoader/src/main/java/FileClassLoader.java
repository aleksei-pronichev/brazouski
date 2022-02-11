import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File("src\\main\\resources\\" + name + ".class");
        if (!f.isFile())
            throw new ClassNotFoundException(name);
        InputStream ins = null;
        try {
            ins = new BufferedInputStream(new FileInputStream(f));
            byte[] b = new byte[(int) f.length()];
            ins.read(b);
            return defineClass(name, b, 0, b.length);
        } catch (FileNotFoundException e) {
            System.out.println("File not found - " + e.getClass().getName());
        } catch (IOException e) {
            System.out.println("Something gone wrong - " + e.getClass().getName());
        } finally {
            try {
                assert ins != null;
                ins.close();
            } catch (IOException e) {
                System.out.println("Something gone wrong - " + e.getClass().getName());
            }
        }
        throw new ClassNotFoundException();
    }
}
