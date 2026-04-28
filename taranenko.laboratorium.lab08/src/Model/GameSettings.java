package Model;

import java.util.ArrayList;
import java.util.List;

//Moim zdaniem lepiej zrobić public abstract final class
public interface GameSettings
{
   final List<Integer> points = new ArrayList<>(List.of(100, 70, 40, 20, 10));
   final int consolation_points = 10;
   final int game_numbers = 3;
   final int round_numbers = 5;

   public void printGameSetting();
}
