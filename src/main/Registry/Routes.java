package main.Registry;

import main.Builders.Route;
import main.DynamicRouter;
import main.Handlers.Resource;

import java.util.HashMap;

/**
 * Created by nystrom on 12/8/15.
 */
public class Routes extends HashMap<String, Route> {
    public Routes () {

    }

    public Routes build(){
        Route formRoute = new Route();
        formRoute.setHandler(new Resource());
        this.put("/form", formRoute);

        Route methodOptions = new Route();
        methodOptions.setHandler(new Resource());
        this.put("/method_options", methodOptions);

        Route logsRoute = new Route();
        logsRoute.setAuthentication("admin", "hunter2", "challenge");
        this.put("/logs", logsRoute);

        return this;
    }

}
