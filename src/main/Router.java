package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nystrom on 11/5/15.
 */
public class Router {
    static List<String> resources = new ArrayList();

    public static void addResource(String resource){
        resources.add(resource);
    }

    public static boolean hasPath(String resource){
        return resources.contains(resource);
    }
}
