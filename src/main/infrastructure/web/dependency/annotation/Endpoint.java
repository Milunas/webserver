package main.infrastructure.web.dependency.annotation;

public @interface Endpoint {
    public String path() default "";
    public String method() default "";
}
