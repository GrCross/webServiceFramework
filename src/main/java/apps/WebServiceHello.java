package apps;

import java.io.File;

public class WebServiceHello {

    @Web("cuadrado")
    public static String  square(){
        return "<html> " +
                "<head>Hello World</head>" +
                "</html>";
    }

    @Web("elesis")
    public static File image(){
        File file = new File("resources/elesis.jpeg");
        return file;
    }

    @Web("music")
    public static File song(){
        File file = new File("resources/Aaro - Indestructable.mp3");
        return file;
    }

    @Web("video")
    public static File video(){
        File file = new File("resources/videoSong.mp4");
        return file;
    }

    @Web("landscape")
    public static File landscape(){
        File file = new File("resources/shingekyNoCat.jpg");
        return file;
    }

    @Web("itsDown")
    public static File itsDown(){
        File file = new File("resources/itsMeOrItsDown.html");
        return file;
    }



}
