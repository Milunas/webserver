package main.infrastructure.web.dependency;

import main.infrastructure.web.dependency.annotation.Component;
import main.infrastructure.web.dependency.annotation.Endpoint;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DependencyLoader {

    public List<String> getUrlsFromMethods() throws NoSuchFieldException, IllegalAccessException {
        List<Method> methods = getAllLoadedMethods();
        List<String> urls = new ArrayList<>();
        for (Method method : methods){
            Endpoint endpoint = method.getAnnotation(Endpoint.class);
            if (endpoint != null){
                String httpMethod = endpoint.method();
                String path = endpoint.path();
                urls.add(httpMethod);
                urls.add(path);
            }
        }
        return urls;
    }

    private List<Method> getAllLoadedMethods() throws NoSuchFieldException, IllegalAccessException {
        List<Class> allLoaderClassList = getAllComponents();
        List<Method> methodList = new ArrayList<>();
        for (Class loadedClass: allLoaderClassList){
            Method[] methods = loadedClass.getDeclaredMethods();
            methodList.addAll(Arrays.asList(methods));
        }
        return methodList;
    }

    private List<Class> getAllComponents() throws NoSuchFieldException, IllegalAccessException {
        return getAllLoadedClasses().stream()
                .filter(c -> c != DependencyLoader.class)
                .filter(c -> c.getDeclaredAnnotation(Component.class) != null)
                .collect(Collectors.toList());
    }

    private List<Class> getAllLoadedClasses() throws IllegalAccessException, NoSuchFieldException {
        Field classField = ClassLoader.class.getDeclaredField("classes");
        classField.setAccessible(true);
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return  (List<Class>) classField.get(loader);
    }

}
