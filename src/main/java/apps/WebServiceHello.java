package apps;

public class WebServiceHello {

    @Web("cuadrado")
    public static String  square(){
        return "<html> " +
                "<head>Hello World</head>" +
                "</html>";
    }
}
