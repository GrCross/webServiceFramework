package apps;

import java.io.File;

public class WebServiceHello {

    @Web("cuadrado")
    public static String  square(){
        return "<html> " +
                "<head>Hello World</head>" +
                "</html>";
    }

    @Web("Elesis")
    public static File image(){
        File file = new File("resources/elesis.jpeg");
        return file;
    }

    @Web("Music")
    public static File songzzza(){
        File file = new File("resources/Aaro - Indestructable.mp3");
        return file;
    }


}
