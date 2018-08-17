package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import com.johnbalderson.javajokelibrary.JavaJoke;

import java.util.Random;

import javax.inject.Named;

import static com.johnbalderson.javajokelibrary.JavaJoke.JokesArray;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)


public class MyEndpoint {

    /** A simple endpoint method that takes in a joke and gives the answer back */
    @ApiMethod(name = "selectJoke")
    public MyBean selectJoke(@Named("joke") int index) {
        MyBean response = new MyBean();

        index = new Random().nextInt(JokesArray.length);
        response.setData(JokesArray[index]);
        return response;
    }

}
