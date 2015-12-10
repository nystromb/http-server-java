# Cob Spec Java Server
*now serving tic-tac-toe!*

To run the HttpServer.jar file use the command make sure you are in the current working directory of the .jar then run the command:

`java -jar HttpServer.jar -p 5000 -d /some/path/to/serve/

Make sure you have a trailing / at the end of your path name!

The server will not be able to log files if you don't have a logs/ directory in the root directory you specify. 

You can create your own routes in the Registry/Routes.java file

If a route is not defined in the Routes.java folder, the server will return a 404 Not Found response.
