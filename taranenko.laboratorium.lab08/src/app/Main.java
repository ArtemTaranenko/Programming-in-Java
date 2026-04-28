package app;

import Controller.GameController;

public class Main
{
    public static void main()
    {
        GameController Controller = new GameController();
        Controller.printGameSetting();
        Controller.startGame();
    }
}
