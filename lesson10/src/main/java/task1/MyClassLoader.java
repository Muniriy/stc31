package task1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

    /**
     * Finds the class with the specified <a href="#name">binary name</a>.
     * This method should be overridden by class loader implementations that
     * follow the delegation model for loading classes, and will be invoked by
     * the {@link #loadClass <tt>loadClass</tt>} method after checking the
     * parent class loader for the requested class.  The default implementation
     * throws a <tt>ClassNotFoundException</tt>.
     *
     * @param name The <a href="#name">binary name</a> of the class
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class could not be found
     * @since 1.2
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File classFile = new File(name + ".class");
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(classFile))) {
            byte[] content = new byte[(int) classFile.length()];
            bis.read(content, 0, content.length);
            final Class<?> clazz = defineClass(name, content,0, content.length);
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
