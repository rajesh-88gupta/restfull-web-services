package com.techsolvers.rest.webservice.restfull_web_services.helloworld;

public class HelloWorldBean {
    private String helloWorld;
    public String getHelloWorld() {
        return helloWorld;
    }

    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }


    public HelloWorldBean(String helloWorld) {
        this.helloWorld =helloWorld;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "helloWorld='" + helloWorld + '\'' +
                '}';
    }
}
