/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package servlets.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.servlets",
                ownerName = "backend.servlets",
                packagePath = ""
        )
)
public class MyEndpoint {

    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        response.setData("Hi, " + name);

        return response;
    }
    @ApiMethod(name = "getAnswer")
    public MyBean getAnswer() {
        MyBean response = new MyBean();
        String data = new HttpClient().get("http://ip.jsontest.com");
        response.setData(data);
        return response;
    }
/*
    @ApiMethod(name = "getSmth", path = "")
    public MyBean getSmth(MyBean mybean) {
             MyBean response = new MyBean();

        return response;
    }
    */

}
