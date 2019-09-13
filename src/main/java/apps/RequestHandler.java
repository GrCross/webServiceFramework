package apps;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class RequestHandler implements Runnable{

    private final Socket socket;
    private static BufferedReader in = null;
    private static BufferedOutputStream dataOut = null;
    Map<String,Handler> URLHandleList = new HashMap<>();

    public RequestHandler(Socket socket,Map<String,Handler> URLHandleList) {
        this.socket = socket;
        this.URLHandleList = URLHandleList;
    }

    @Override
    public void run() {
        try{
            PrintStream out=new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataOut = new BufferedOutputStream(socket.getOutputStream());
            String inputLine, outPutLine;
            inputLine = in.readLine();

            if(inputLine == null){
                inputLine = "GET /apps/cuadrado HTTP/1.1";
            }
            StringTokenizer st = new StringTokenizer(inputLine);

            ;
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
                String mimeType="";
                if(request.equals("/")){
                    Handler handlerTemp = this.URLHandleList.get("/apps/cuadrado");
                    Object answer = handlerTemp.processor();
                    InputStream inStream=new FileInputStream((File)answer);

                    mimeType= new MimetypesFileTypeMap().getContentType((File)answer);
                    out.print("HTTP/1.0 200 OK\r\n"+
                            "Content-type: "+mimeType+"\r\n\r\n");
                    byte[]fileData = new byte[5000];
                    int n;
                    while ((n = inStream.read(fileData))>0) out.write(fileData, 0, n);
                }else{
                    if(handler == null){
                        mimeType="text/html";
                        out.print("HTTP/1.0 404 NOT_FOUND\r\n"+
                                "Content-type: "+mimeType+"\r\n\r\n");
                    }else{
                        Object answer = handler.processor();
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
                    }
                }

                out.close(); in.close();

            }catch (Exception e){

                String mimeType="";
                mimeType="text/html";
                out.print("HTTP/1.0 404 Not Found\r\n"+
                        "Content-type: "+mimeType+"\r\n\r\n");
                e.printStackTrace();
            }
        }catch (Exception e){

        }

    }
}
