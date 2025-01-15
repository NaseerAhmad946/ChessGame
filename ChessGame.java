/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chessgame;

/**
 *
 * @author naseer
 */
//
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

enum Color1{
white,Black;
}
class Board{
public Piece board[][]=new Piece[8][8];

public Board(){
this.board = new Piece[8][8];
this.Init_Board();
}

private void Init_Board(){
    for (int i = 0; i < 8; i++) {
        board[1][i] = new pawn(Color1.white, new Position(1, i)); 
        board[6][i] = new pawn(Color1.Black, new Position(6, i)); 
    }

  
    board[0][0] = new Rook(Color1.white, new Position(0, 0));
    board[0][7] = new Rook(Color1.white, new Position(0, 7));
    board[7][0] = new Rook(Color1.Black, new Position(7, 0));
    board[7][7] = new Rook(Color1.Black, new Position(7, 7));

 
    board[0][1] = new Knight(Color1.white, new Position(0, 1));
    board[0][6] = new Knight(Color1.white, new Position(0, 6));
    board[7][1] = new Knight(Color1.Black, new Position(7, 1));
    board[7][6] = new Knight(Color1.Black, new Position(7, 6));


    board[0][2] = new Bishop(Color1.white, new Position(0, 2));
    board[0][5] = new Bishop(Color1.white, new Position(0, 5));
    board[7][2] = new Bishop(Color1.Black, new Position(7, 2));
    board[7][5] = new Bishop(Color1.Black, new Position(7, 5));

  
    board[0][3] = new Queen(Color1.white, new Position(0, 3));
    board[7][3] = new Queen(Color1.Black, new Position(7, 3));


    board[0][4] = new King(Color1.white, new Position(0, 4));
    board[7][4] = new King(Color1.Black, new Position(7, 4));

    
    for (int i = 2; i < 6; i++) {
        for (int j = 0; j < 8; j++) {
            board[i][j] = null;
        }
    }
}
}

class Position{
    private int row;
    private int column;
    public Position(int row,int column){
    this.row = row;
    this.column = column;
    }
  public int get_row(){
  return row;
  }
  public int get_column(){
  return column;
  }
}
abstract class Piece{
Color1 color;
Position position;
public Piece(Color1 color,Position position){
this.color = color;
this.position = position;
}
public Position get_position(){
return position;
}
public void set_position(Position p){
this.position = p;
}
abstract public boolean isvalideMove(Position StartPosition,Position EndPosition,Board board_obj);
}
//pawn 
class pawn extends Piece{
public pawn(Color1 color,Position position){
super(color,position);
}
public boolean isvalideMove(Position StartPosition,Position EndPosition,Board board_obj){
   
//    System.out.println(EndPosition.get_row());
//    System.out.println(EndPosition.get_column());
//System.out.println("Validating move from " + StartPosition.get_row() + "," + StartPosition.get_column() +
//                   " to " + EndPosition.get_row() + "," + EndPosition.get_column());

int row_dif = EndPosition.get_row() - StartPosition.get_row();
int col_dif = EndPosition.get_column() - StartPosition.get_column();
    if(this.color == color.Black ){
        // the below code needs to be checked again specially the board_obj
        if(row_dif == -1 && col_dif == 0 && board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null){
          
        return true;
        }
        if(row_dif == -1 &&(col_dif  ==-1  || col_dif == 1)&&board_obj.board[EndPosition.get_row()][EndPosition.get_column()]!=null){
            if(board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color == color.white){
              
        return true;
            }
      
        }
        if(StartPosition.get_row() == 6 &&row_dif == -2&& board_obj.board[EndPosition.get_row()][EndPosition.get_column()] == null && board_obj.board[StartPosition.get_row()-1][StartPosition.get_column()] == null ){
        System.out.println(EndPosition.get_row());
            
        return true;
        }
}
    if(this.color == color.white){
        // the below code needs to be checked again specially the board_obj
        if(row_dif == 1 && col_dif == 0 && board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null){
        return true;
        }
        if(row_dif == 1 &&(col_dif  ==-1  || col_dif == 1)&&board_obj.board[EndPosition.get_row()][EndPosition.get_column()]!=null){
        if((board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color == color.Black)){
            return true;
        }
        }
        if(StartPosition.get_row() == 1 &&row_dif == 2&& board_obj.board[EndPosition.get_row()][EndPosition.get_column()] == null && board_obj.board[StartPosition.get_row()+1][StartPosition.get_column()] == null ){
        return true;
        }       
}  
return false;
}
}

//Rook  
class Rook extends Piece{
public Rook(Color1 color,Position position){
super(color,position);
}
public boolean isvalideMove(Position StartPosition,Position EndPosition,Board board_obj){
    int counter = 0;
//        System.out.println(EndPosition.get_row());
    
    int row_diff = EndPosition.get_row() - StartPosition.get_row();
    int col_diff = EndPosition.get_column() - StartPosition.get_column();
    if (row_diff != 0 && col_diff != 0) {
    return false; // Invalid move (not purely vertical or horizontal)
}
if(row_diff == 0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color) && col_diff>0){
    
    for(int i = StartPosition.get_column()+1; i< EndPosition.get_column(); i++){
        if(board_obj.board[StartPosition.get_row()][i] == null ){
        counter++;
        }
}
  if(((EndPosition.get_column() - StartPosition.get_column())-1) == counter){
      return true;
  }  
}
if(row_diff == 0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color) && col_diff<0){
    //here we have to check wheather its  StartPosition.get_column() +1 or -1
    for(int i = StartPosition.get_column()-1; i> EndPosition.get_column(); i--){
        if(board_obj.board[StartPosition.get_row()][i] == null ){
        counter++;
        }
}
 if (-((EndPosition.get_column() - StartPosition.get_column()) + 1) == counter) { 
            return true;
        }
}
if(col_diff == 0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color) && row_diff>0){
    for(int i = StartPosition.get_row()+1; i< EndPosition.get_row(); i++){
        if(board_obj.board[i][StartPosition.get_column()] == null ){
        counter++;
        }
}
  if(((EndPosition.get_row() - StartPosition.get_row())-1) == counter){
      return true;
  }  
}
if(col_diff == 0 &&(board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color) && row_diff<0){
    //here we have to check wheather its  StartPosition.get_column() +1 or -1
    for(int i = StartPosition.get_row()-1; i> EndPosition.get_row(); i--){
        if(board_obj.board[i][StartPosition.get_column()] == null ){
        counter++;
        }
}
 if (-((EndPosition.get_row() - StartPosition.get_row()) + 1) == counter) { 
            return true;
        } 
}
return false;
}
}
class Bishop extends Piece{
public Bishop(Color1 color, Position position){
super(color,position);
}
public boolean isvalideMove(Position StartPosition,Position EndPosition,Board board_obj){
  
    int row_dif = EndPosition.get_row() - StartPosition.get_row();
    int col_dif = EndPosition.get_column() - StartPosition.get_column();
      if (Math.abs(row_dif) != Math.abs(col_dif)) {
    return false; 
}
    if(row_dif >0 && col_dif<0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]== null || board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color != this.color)){
    int col = StartPosition.get_column()-1;
    int row = StartPosition.get_row()+1;
    int counter1= 0;
    int steps = Math.abs(row_dif) -1;
    for(int i=1;i<=steps;i++ ){
    if(board_obj.board[row][col] == null){
    counter1++;
    } 
    col--;
    row++;
    }
    if(counter1 == steps){
    return true;
    }
    }
    if(row_dif>0 && col_dif>0 &&(board_obj.board[EndPosition.get_row()][EndPosition.get_column()]== null || board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color != this.color)){
    int col = StartPosition.get_column()+1;
    int row = StartPosition.get_row()+1;
    int counter4 =0;
    for(int i=1;i<=Math.abs(row_dif)-1;i++ ){
    if(board_obj.board[row][col] == null){
    counter4++;
    } 
    col++;
    row++;
    }
    if(counter4 == Math.abs(row_dif)-1){
    return true;
    }
    }
    
    if(row_dif <0 && col_dif>0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]== null || board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color != this.color)){
    int col = StartPosition.get_column()+1;
    int row = StartPosition.get_row()-1;
    int counter2= 0;
    int steps = Math.abs(row_dif) -1;
    for(int i=1;i<=steps;i++ ){
    if(board_obj.board[row][col] == null){
    counter2++;
    } 
    col++;
    row--;
    }
    if(counter2 == steps){
    return true;
    }
    }
    if(row_dif<0 && col_dif<0 &&(board_obj.board[EndPosition.get_row()][EndPosition.get_column()]== null || board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color != this.color)){
    int col = StartPosition.get_column()-1;
    int row = StartPosition.get_row()-1;
    int counter3 =0;
    for(int i=1;i<=Math.abs(row_dif)-1;i++ ){
    if(board_obj.board[row][col] == null){
    counter3++;
    } 
    col--;
    row--;
    }
    if(counter3 == Math.abs(row_dif)-1){
    return true;
    }
    } 
    return false;
}
}
class Queen extends Piece{
public Queen(Color1 color,Position position){
super(color,position);
}
public boolean isvalideMove(Position StartPosition,Position EndPosition,Board board_obj){
     int row_dif = EndPosition.get_row() - StartPosition.get_row();
    int col_dif = EndPosition.get_column() - StartPosition.get_column();
    
    
 if (!(row_dif == 0 || col_dif == 0 || Math.abs(row_dif) == Math.abs(col_dif))) {
    return false;
}

      // rook move for queen
      int counter = 0; 
      if(row_dif == 0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color) && col_dif>0){
    
    for(int i = StartPosition.get_column()+1; i< EndPosition.get_column(); i++){
        if(board_obj.board[StartPosition.get_row()][i] == null ){
        counter++;
        }
}
  if(((EndPosition.get_column() - StartPosition.get_column())-1) == counter){
      return true;
  }  
}
if(row_dif == 0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color) && col_dif<0){
    //here we have to check wheather its  StartPosition.get_column() +1 or -1
    for(int i = StartPosition.get_column()-1; i> EndPosition.get_column(); i--){
        if(board_obj.board[StartPosition.get_row()][i] == null ){
        counter++;
        }
}
 if (-((EndPosition.get_column() - StartPosition.get_column()) + 1) == counter) { 
            return true;
        }
}
if(col_dif == 0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color) && row_dif>0){
    for(int i = StartPosition.get_row()+1; i< EndPosition.get_row(); i++){
        if(board_obj.board[i][StartPosition.get_column()] == null ){
        counter++;
        }
}
  if(((EndPosition.get_row() - StartPosition.get_row())-1) == counter){
      return true;
  }  
}
if(col_dif == 0 &&(board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color) && row_dif<0){
    //here we have to check wheather its  StartPosition.get_column() +1 or -1
    for(int i = StartPosition.get_row()-1; i> EndPosition.get_row(); i--){
        if(board_obj.board[i][StartPosition.get_column()] == null ){
        counter++;
        }
}
 if (-((EndPosition.get_row() - StartPosition.get_row()) + 1) == counter) { 
            return true;
        } 
}

// bishop move for queen
      if(row_dif >0 && col_dif<0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]== null || board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color != this.color)){
    int col = StartPosition.get_column()-1;
    int row = StartPosition.get_row()+1;
    int counter1= 0;
    int steps = Math.abs(row_dif) -1;
    for(int i=1;i<=steps;i++ ){
    if(board_obj.board[row][col] == null){
    counter1++;
    } 
    col--;
    row++;
    }
    if(counter1 == steps){
    return true;
    }
    }
    if(row_dif>0 && col_dif>0 &&(board_obj.board[EndPosition.get_row()][EndPosition.get_column()]== null || board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color != this.color)){
    int col = StartPosition.get_column()+1;
    int row = StartPosition.get_row()+1;
    int counter4 =0;
    for(int i=1;i<=Math.abs(row_dif)-1;i++ ){
    if(board_obj.board[row][col] == null){
    counter4++;
    } 
    col++;
    row++;
    }
    if(counter4 == Math.abs(row_dif)-1){
    return true;
    }
    }
    
    if(row_dif <0 && col_dif>0 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]== null || board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color != this.color)){
    int col = StartPosition.get_column()+1;
    int row = StartPosition.get_row()-1;
    int counter2= 0;
    int steps = Math.abs(row_dif) -1;
    for(int i=1;i<=steps;i++ ){
    if(board_obj.board[row][col] == null){
    counter2++;
    } 
    col++;
    row--;
    }
    if(counter2 == steps){
    return true;
    }
    }
    if(row_dif<0 && col_dif<0 &&(board_obj.board[EndPosition.get_row()][EndPosition.get_column()]== null || board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color != this.color)){
    int col = StartPosition.get_column()-1;
    int row = StartPosition.get_row()-1;
    int counter3 =0;
    for(int i=1;i<=Math.abs(row_dif)-1;i++ ){
    if(board_obj.board[row][col] == null){
    counter3++;
    } 
    col--;
    row--;
    }
    if(counter3 == Math.abs(row_dif)-1){
    return true;
    }
    }   
    return false; 
}
}
class Knight extends Piece{
    public Knight(Color1 color,Position position){
    super(color,position);
    }
    public boolean isvalideMove(Position StartPosition,Position EndPosition,Board board_obj){
        int row_diff = Math.abs(EndPosition.get_row() - StartPosition.get_row());
        int col_diff = Math.abs(EndPosition.get_column() - StartPosition.get_column());
        if(row_diff+col_diff == 3 && (board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color)){
        return true;
        }
    return false;
    }  
}

class King extends Piece{
    protected boolean is_in_check;
public King(Color1 color,Position position){
super(color,position);
}
public boolean isvalideMove(Position StartPosition,Position EndPosition,Board board_obj){
    int row_diff = Math.abs(EndPosition.get_row() - StartPosition.get_row());
    int col_diff = Math.abs(EndPosition.get_column() - StartPosition.get_column());
    if(row_diff<=1 && col_diff <=1&&(board_obj.board[EndPosition.get_row()][EndPosition.get_column()]==null|| board_obj.board[EndPosition.get_row()][EndPosition.get_column()].color!=this.color)){
        return true;
    }
    return false;
}
}

class Gamelogics{
private Board board;
// the following variable is unused 
private boolean ThisKingis_in_check;
protected boolean GameOver;
// we have to modify the constructor
public Gamelogics(Board board) {
    this.board = board;
}

public boolean isPositionOnBoard(Position position) {
    int row = position.get_row();
    int col = position.get_column();
    return row >= 0 && row < 8 && col >= 0 && col < 8;
}

public King FindKing(Color1 color1) {
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            if (board.board[row][col] instanceof King && 
                board.board[row][col].color == color1) {
                return (King)board.board[row][col]; 
            }
        }
    } 
    return null; // King not found
}
public boolean King_in_Check(King king,Color1 color){
    King found_king = FindKing(color);
for(int row = 0;row<8;row++){
    for(int col =0 ;col<8;col++){
        if (board.board[row][col] != null && board.board[row][col].color != color){
            if(board.board[row][col].isvalideMove(new Position(row,col), found_king.get_position(), board)){
                found_king.is_in_check = true;
                return true;
            }
        }
    }   
}
return false;
}
public void move(Piece piece, Position EndPosition) {
    Piece targetPiece = board.board[EndPosition.get_row()][EndPosition.get_column()];
    if (targetPiece instanceof King) {
        System.out.println("Cannot capture the King. Move is invalid.");
        return; // Exit the method
    }
    if (piece.isvalideMove(piece.position, EndPosition, board)) {
        // Save current state for potential rollback
        Piece oldPiece = board.board[EndPosition.get_row()][EndPosition.get_column()];
        Position originalPosition = piece.position;
        if(!isPositionOnBoard(EndPosition)){
            // Gui Work
            System.out.println("There is no such position on board");
        }
        // Make the tentative move
        board.board[originalPosition.get_row()][originalPosition.get_column()] = null;
        board.board[EndPosition.get_row()][EndPosition.get_column()] = piece;
        piece.position = EndPosition;

        // Check if the move leaves the King in check
        King king = FindKing(piece.color);
        if (King_in_Check(king, piece.color)) {
            // Revert the move
            board.board[originalPosition.get_row()][originalPosition.get_column()] = piece;
            board.board[EndPosition.get_row()][EndPosition.get_column()] = oldPiece;
            piece.position = originalPosition;

            // Feedback to the user
            System.out.println("Cannot move: King would be in check.");
            return;
        }

        // Successful move
        System.out.println("Move completed.");
        // Perform GUI updates here if needed
    } else {
        // Feedback for an invalid move
        System.out.println("Invalid move.");
    }
}
public boolean CheckMate(Color1 color) {
    King king_for_checkmate = FindKing(color);
    Position KingPosition = king_for_checkmate.get_position();

    // Check if the King is in check
    if (!King_in_Check(king_for_checkmate, color)) {
        return false; // Not in check, therefore not checkmate
    }

    // Check if the King can move to a safe square
    int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};

    for (int i = 0; i < rowOffsets.length; i++) {
        int newRow = KingPosition.get_row() + rowOffsets[i];
        int newCol = KingPosition.get_column() + colOffsets[i];
        Position newPosition = new Position(newRow, newCol);

        if (isPositionOnBoard(newPosition) && 
                (board.board[newRow][newCol] == null || 
                 board.board[newRow][newCol].color != king_for_checkmate.color) && 
                king_for_checkmate.isvalideMove(KingPosition, newPosition, board)) {

            // Temporarily move the King
            Piece oldPiece = board.board[newRow][newCol];
            board.board[newRow][newCol] = king_for_checkmate;
            king_for_checkmate.position = newPosition;
            board.board[KingPosition.get_row()][KingPosition.get_column()] = null;

            // Check if the King is still in check after the move
            if (!King_in_Check(king_for_checkmate, color)) {
                // Revert the King's position
                board.board[KingPosition.get_row()][KingPosition.get_column()] = king_for_checkmate;
                board.board[newRow][newCol] = oldPiece;
                king_for_checkmate.position = KingPosition;

                // King can escape check, not checkmate
                return false;
            }

            // Revert the King's position
            board.board[KingPosition.get_row()][KingPosition.get_column()] = king_for_checkmate;
            board.board[newRow][newCol] = oldPiece;
            king_for_checkmate.position = KingPosition;
        }
    }

    // Check if any other piece can block the check or capture the checking piece
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            
            
            
            if (board.board[row][col] != null && board.board[row][col].color == king_for_checkmate.color) {
                
                Piece Moving_piece = board.board[row][col];
                Position currentPosition = Moving_piece.get_position();

                // Check all possible moves for each piece type
                for (int row1 = 0; row1 < 8; row1++) {
                    for (int col1 = 0; col1 < 8; col1++) {
                        Position newPosition = new Position(row1, col1);

                        if (Moving_piece.isvalideMove(currentPosition, newPosition, board)) {
                            // Temporarily move the piece
                            Piece tempPiece = board.board[row1][col1];
                            board.board[row1][col1] = Moving_piece;
                            board.board[currentPosition.get_row()][currentPosition.get_column()] = null;
                            Moving_piece.position = newPosition;

                            // Check if the King is still in check after the move
                            if (!King_in_Check(king_for_checkmate, color)) {
                                // Revert the move
                                board.board[currentPosition.get_row()][currentPosition.get_column()] = Moving_piece;
                                board.board[row1][col1] = tempPiece;
                                Moving_piece.position = currentPosition;

                                // Move is valid, not checkmate
                                return false;
                            }

                            // Revert the move
                            board.board[currentPosition.get_row()][currentPosition.get_column()] = Moving_piece;
                            board.board[row1][col1] = tempPiece;
                            Moving_piece.position = currentPosition;
                        }
                    }
                }
            }
        }
    }

    // If no escape moves are found, it's checkmate
    System.out.println(color + " is in checkmate! Game over.");
    return true;
}
}

public class ChessGame extends JFrame {
    private JPanel chessBoard;
    private JLabel[][] squares;
 
//    private Board board;
    private Piece selectedPiece;
    private Position selectedPosition;
    boolean WhiteTurn = true;
    private ImageIcon whitePawnIcon, blackPawnIcon, whiteKnightIcon, blackKnightIcon, whiteBishopIcon, blackBishopIcon;
    private ImageIcon whiteRookIcon, blackRookIcon, whiteQueenIcon, blackQueenIcon, whiteKingIcon, blackKingIcon;
    Board board = new Board();
    Gamelogics GameLogic = new Gamelogics(board);
    public ChessGame() {
        setTitle("Chess Game");
        setSize(800, 800);
         setLayout(new BorderLayout()); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        chessBoard = new JPanel(new GridLayout(8, 8));
        squares = new JLabel[8][8];

        
        // Load piece images
        try {
//            ImageIcon whitePawnIcon=new ImageIcon(ClassLoader.getSystemResource("icons/White_Pawn.png"));
//            Image img=whitePawnIcon.getImage().getScaledInstance(9, 9, Image.SCALE_DEFAULT);
//            whitePawnIcon.setImage(img);
           // Assuming you have a consistent naming convention for your chess piece images:
// White pieces: W<PieceName>.png (e.g., WPawn.png, WKnight.png)
// Black pieces: B<PieceName>.png (e.g., BPawn.png, BKnight.png)

String baseImagePath = "/home/naseer/Downloads/"; 

whitePawnIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "WPawn.png")));
blackPawnIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "BPawn.png")));
whiteKnightIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "WKnight.png")));
blackKnightIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "BKnight.png")));
whiteBishopIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "WBishop.png")));
blackBishopIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "BBishop.png")));
whiteRookIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "WRook.png")));
blackRookIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "BRook.png")));
whiteQueenIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "WQueen.png")));
blackQueenIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "BQueen.png")));
whiteKingIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "WKing.png")));
blackKingIcon = new ImageIcon(ImageIO.read(new File(baseImagePath + "BKing.png")));

            // Scale images
            whitePawnIcon.setImage(whitePawnIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            blackPawnIcon.setImage(blackPawnIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
           whiteKnightIcon.setImage(whiteKnightIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
           blackKnightIcon.setImage(blackKnightIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            whiteBishopIcon.setImage(whiteBishopIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            blackBishopIcon.setImage(blackBishopIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            whiteRookIcon.setImage(whiteRookIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            blackRookIcon.setImage(blackRookIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            whiteQueenIcon.setImage(whiteQueenIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            blackQueenIcon.setImage(blackQueenIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            whiteKingIcon.setImage(whiteKingIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
            blackKingIcon.setImage(blackKingIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading piece images.");
        }

        initializeBoard();
        
        add(chessBoard);
   
          
        setVisible(true);
    }
private void handleSquareClick(int row, int col) {
    Piece clickedPiece = board.board[7 - row][col]; // Get the piece at the clicked position

    if (selectedPiece == null) { // No piece is selected
        if (clickedPiece != null) { // If there's a piece at the clicked position
            // Check if it's the current player's turn
            if (WhiteTurn && clickedPiece.color == Color1.white || 
                !WhiteTurn && clickedPiece.color == Color1.Black) {
                selectedPiece = clickedPiece; // Select the piece
                selectedPosition = new Position(7 - row, col); // Remember the position
                System.out.println("Selected piece: " + clickedPiece);
            }
        }
    } else { // A piece is already selected, so we need to move it
        Position targetPosition = new Position(7 - row, col); // Target position for the move

        if (selectedPiece.isvalideMove(selectedPiece.position, targetPosition, board)) { // If the move is valid
            // Save the current state for possible revert
            Piece originalTargetPiece = board.board[targetPosition.get_row()][targetPosition.get_column()];
            Position originalPosition = selectedPiece.position;

            // Execute the move
            GameLogic.move(selectedPiece, targetPosition);

            // Check if the move leaves the king in check
            King king = GameLogic.FindKing(selectedPiece.color);
            if (GameLogic.King_in_Check(king, selectedPiece.color)) {
                // Revert the move (undo changes)
                board.board[originalPosition.get_row()][originalPosition.get_column()] = selectedPiece; // Move piece back
                board.board[targetPosition.get_row()][targetPosition.get_column()] = originalTargetPiece; // Restore target piece
                selectedPiece.position = originalPosition; // Restore piece's original position

                // Show popup
                JOptionPane.showMessageDialog(this, 
                    "Invalid move: King is in check.", 
                    "Invalid Move", 
                    JOptionPane.WARNING_MESSAGE);
            } else {
                // Move was successful, toggle turn
                WhiteTurn = !WhiteTurn;
                updateBoard(); // Update the GUI

                // Check for checkmate
                Color1 opponentColor = WhiteTurn ? Color1.white : Color1.Black;
                if (GameLogic.CheckMate(opponentColor)) {
                    JOptionPane.showMessageDialog(this, 
                        opponentColor + " is in checkmate! Game over.", 
                        "Game Over", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "Invalid move.", 
                "Invalid Move", 
                JOptionPane.WARNING_MESSAGE);
        }

        // Clear selection
        selectedPiece = null;
        selectedPosition = null;
    }
}





    private void initializeBoard() {
        
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JPanel squarePanel = new JPanel();
                if ((row + col) % 2 == 0) {
                    squarePanel.setBackground(Color.WHITE);
                } else {
                    squarePanel.setBackground(Color.GRAY);
                }

                JLabel square = new JLabel();
                squares[row][col] = square;

                // Adjust row index to invert the board's visual representation
                int invertedRow = 7 - row;

                if (board.board[invertedRow][col] != null) {
                    Piece piece = board.board[invertedRow][col];
                    if (piece instanceof pawn) {
                        if (piece.color == Color1.white) {
                            square.setIcon(whitePawnIcon);
                        } else {
                            square.setIcon(blackPawnIcon);
                        }
                    } else if (piece instanceof Knight) {
                        if (piece.color == Color1.white) {
                            square.setIcon(whiteKnightIcon);
                        } else {
                            square.setIcon(blackKnightIcon);
                        }
                    } else if (piece instanceof Bishop) {
                        if (piece.color == Color1.white) {
                            square.setIcon(whiteBishopIcon);
                        } else {
                            square.setIcon(blackBishopIcon);
                        }
                    } else if (piece instanceof Rook) {
                        if (piece.color == Color1.white) {
                            square.setIcon(whiteRookIcon);
                        } else {
                            square.setIcon(blackRookIcon);
                        }
                    } else if (piece instanceof Queen) {
                        if (piece.color == Color1.white) {
                            square.setIcon(whiteQueenIcon);
                        } else {
                            square.setIcon(blackQueenIcon);
                        }
                    } else if (piece instanceof King) {
                        if (piece.color == Color1.white) {
                            square.setIcon(whiteKingIcon);
                        } else {
                            square.setIcon(blackKingIcon);
                        }
                    }
                    
                }
                 final int finalRow = row;
            final int finalCol = col;
            squarePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleSquareClick(finalRow, finalCol);  // Passes the clicked square's position to the handler
                }
            });

                squarePanel.add(square);
                chessBoard.add(squarePanel);
            }
        }
        System.out.println("Chess board is initilzed");
    }
    private void updateBoard() {
       
    // Loop through all rows and columns of the chessboard
    for (int row = 0; row < 8; row++) {
        for (int col = 0; col < 8; col++) {
            Piece piece = board.board[7 - row][col];  // Get the piece at the corresponding position

            // Get the label for the current square
            JLabel square = squares[row][col];

            // If there is a piece on the square, update the icon accordingly
            if (piece != null) {
                if (piece instanceof pawn) {
                    if (piece.color == Color1.white) {
                        square.setIcon(whitePawnIcon);
                    } else {
                        square.setIcon(blackPawnIcon);
                    }
                } else if (piece instanceof Knight) {
                    if (piece.color == Color1.white) {
                        square.setIcon(whiteKnightIcon);
                    } else {
                        square.setIcon(blackKnightIcon);
                    }
                } else if (piece instanceof Bishop) {
                    if (piece.color == Color1.white) {
                        square.setIcon(whiteBishopIcon);
                    } else {
                        square.setIcon(blackBishopIcon);
                    }
                } else if (piece instanceof Rook) {
                    if (piece.color == Color1.white) {
                        square.setIcon(whiteRookIcon);
                    } else {
                        square.setIcon(blackRookIcon);
                    }
                } else if (piece instanceof Queen) {
                    if (piece.color == Color1.white) {
                        square.setIcon(whiteQueenIcon);
                    } else {
                        square.setIcon(blackQueenIcon);
                    }
                } else if (piece instanceof King) {
                    if (piece.color == Color1.white) {
                        square.setIcon(whiteKingIcon);
                    } else {
                        square.setIcon(blackKingIcon);
                    }
                }
            } else {
                square.setIcon(null);  // Clear the square if there is no piece
            }
        }
    }
}

    
    
    
    
    

    public static void main(String[] args) {
        new ChessGame();
    }
}
