package cxf.frontend.jaxrs.ex1;

import javax.ws.rs.core.Response;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello1() {
        System.out.println("sayHello1() called by " + Thread.currentThread());
        return "Hello welcome to jax-rs";
    }

    @Override
    public Response sayHello2() {
        System.out.println("sayHello2() called by " + Thread.currentThread());
        return Response.ok("Hello welcome to jax-rs").build();
    }

    @Override
    public Response sayHello3() {
        System.out.println("sayHello3() called by " + Thread.currentThread());
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello jxrs");
        return Response.ok(greeting).build();
    }

    @Override
    public Response sayHello4() {
        System.out.println("sayHello4() called by " + Thread.currentThread());
        return Response.ok("{\"greeting\":\"Hello jxrs2\"}").build();
    }
}
