package app;


import Controllers.Controller;

public class Main {

    public static void main()
    {
        Controller controller = new Controller();
        controller.processData("fishing.txt");
    }
}

