package apps;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.SocketHandler;
import javax.activation.MimetypesFileTypeMap;
import java.io.File;

public class Service {
    private static int port = getPort();
    private static PrintWriter out = null;
    private static BufferedReader in = null;
    private static BufferedOutputStream dataOut = null;
    private static String fileRequest;
    Map<String,Handler> URLHandleList = new HashMap<>();



    public void initialize(String path) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException {


            Class<?> c = Class.forName(path);

            for (Method m: c.getMethods()) {
                System.out.println(m.getAnnotations().toString());
                if(m.isAnnotationPresent(Web.class)){
                    Handler handler = new StaticMethodHandler(m);
                    Web an = m.getAnnotation(Web.class);
                    URLHandleList.put("/apps/"+an.value(),handler);
                    System.out.println("invoking method "+c.getName()+"."+m.getName()+"");
                    System.out.println(m.invoke(null,null));
                }
            }

    }

    public void listen() throws IOException {

        ServerSocket serverSocket = null;

        try {
            //try listen the port
            serverSocket =new ServerSocket(port);

        }catch (Exception e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }


        for( ; ; ) {
            Socket clientSocket = null;
            try {
                //Accept the connection
                System.out.println("Ready for receive ...");
                clientSocket = serverSocket.accept();

            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintStream out=new PrintStream(new BufferedOutputStream(clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            dataOut = new BufferedOutputStream(clientSocket.getOutputStream());
            String inputLine, outPutLine;
            inputLine = in.readLine();
            System.out.println(inputLine);
            StringTokenizer st = new StringTokenizer(inputLine);
            String method = st.nextToken();

            //requested archive
            String request = st.nextToken();

            while ((inputLine = in .readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            try {
                Handler handler = this.URLHandleList.get(request);
                System.out.println(request);
                Object answer = handler.processor();
                String mimeType="";
                
                if(answer instanceof String){
                    mimeType="text/html";
                    out.print("HTTP/1.0 200 OK\r\n"+
                            "Content-type: "+mimeType+"\r\n\r\n");
                    out.print(answer);
                }else{
                    //mimeType="image/jpeg";
                    InputStream inStream=new FileInputStream((File)answer);
                    mimeType= new MimetypesFileTypeMap().getContentType((File)answer);
                    out.print("HTTP/1.0 200 OK\r\n"+
                            "Content-type: "+mimeType+"\r\n\r\n");
                    byte[]fileData = new byte[5000];
                    int n;
                    while ((n = inStream.read(fileData))>0) out.write(fileData, 0, n);
                }
                out.close(); in .close();

            }catch (Exception e){
                System.out.println();
                String mimeType="";
                mimeType="text/html";
                    out.print("HTTP/1.0 404 Not Found\r\n"+
                            "Content-type: "+mimeType+"\r\n\r\n");
                    out.print("<html> " +
                    "<head>404</head>" +
                    "</html>");
                //e.printStackTrace();
            }





        }


    }


    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

}