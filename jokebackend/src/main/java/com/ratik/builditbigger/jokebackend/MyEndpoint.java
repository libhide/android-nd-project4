/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.ratik.builditbigger.jokebackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.ratik.JokeWizard;

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v2",
  namespace = @ApiNamespace(
    ownerDomain = "jokebackend.builditbigger.ratik.com",
    ownerName = "jokebackend.builditbigger.ratik.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that returns a joke */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        MyBean response = new MyBean();
        response.setData(new JokeWizard().getJoke());
        return response;
    }

}
