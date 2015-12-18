package http.router;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nystrom on 12/16/15.
 */
public class Router {
    private static List<Route> routes = new ArrayList<>();

    public static void addRoute(Route route) {
        routes.add(route);
    }

    public static Route getRoute(String path) {
        for(Route route : routes){
            if(route.match(path))
                return route;
        }

        return null;
    }
}
