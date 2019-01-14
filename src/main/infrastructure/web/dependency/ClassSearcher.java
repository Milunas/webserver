package main.infrastructure.web.dependency;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ClassSearcher {

    public void getAllClasses(String packageName) throws Exception {
        getClassesForPackage(packageName).forEach(System.out::println);
    }


    private Collection<Class> getClassesForPackage(String packageName) throws Exception {
        String packagePath = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Set<URL> jarUrls = new HashSet<URL>();

        while (classLoader != null) {
            if (classLoader instanceof URLClassLoader)
                for (URL url : ((URLClassLoader) classLoader).getURLs()){
                    if (url.getFile().endsWith(".jar")) { // may want better way to detect jar files
                        jarUrls.add(url);
                    }
                }
            classLoader = classLoader.getParent();
        }

        Set<Class> classes = new HashSet<Class>();

        for (URL url : jarUrls) {
            JarInputStream stream = new JarInputStream(url.openStream()); // may want better way to open url connections
            JarEntry entry = stream.getNextJarEntry();

            while (entry != null) {
                String name = entry.getName();
                int i = name.lastIndexOf("/");

                if (i > 0 && name.endsWith(".class") && name.substring(0, i).equals(packagePath))
                    classes.add(Class.forName(name.substring(0, name.length() - 6).replace("/", ".")));

                entry = stream.getNextJarEntry();
            }

            stream.close();
        }

        return classes;
    }
}
