package cxf.frontend.jaxws.codefirst.ex3.pojo;

public class HelloWorldImpl implements HelloWorld {

    @Override
    public String greetings(String text) {
        return "Hi " + text;
    }
}
