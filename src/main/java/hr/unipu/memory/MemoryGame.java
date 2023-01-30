package hr.unipu.memory;
import java.util.*;

public class MemoryGame {

    private int turns = 0;
    private int points = 0;
    private final int boardLength = 3;
    private final int boardSize = boardLength * boardLength;
    private final Random random = new Random();

    private final ArrayList<String> memoryBoard = new ArrayList<>(Arrays.asList("", "", "", "", "", "", "", ""));
    private final ArrayList<String> memoryOptions = new ArrayList<>(Arrays.asList("a", "a", "b", "b", "c", "c", "d", "d"));

    public void setupGame(){
        setupMemoryBoard();
        System.out.println(memoryBoard);
    }

    public String getOptionAtIndex(int index){
        return memoryBoard.get(index);
    }

    private void setupMemoryBoard(){
        for (int i = 0; i < boardSize - 1; i++) {
            String memoryOption = memoryOptions.get(i);
            int position = random.nextInt(boardSize - 1);

            while (!Objects.equals(memoryBoard.get(position), "")){
                position = random.nextInt(boardSize - 1);
            }
            memoryBoard.set(position, memoryOption);
        }
    }
    public void resetBoard(){
        System.out.print(points);
        turns = 0;
        points = 0;
        memoryBoard.clear();
        memoryBoard.addAll(Arrays.asList("", "", "", "", "", "", "", ""));
        setupMemoryBoard();
    }
    public boolean checkTwoPositions(int firstIndex, int secondIndex){
       return memoryBoard.get(firstIndex).equals(memoryBoard.get(secondIndex));
    }
}
