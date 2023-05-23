import hsa_ufa.Console;
import java.awt.*;
import java.util.Random;

public class My_Game {

	static Console c = new Console(500, 500, "PLAYZONE");

	public static void main(String[] args) throws InterruptedException {

		c.enableMouse();
		c.enableMouseMotion();
		boolean title_screen = true; //variables used for repetition structures
		boolean game_on = false;
		int mouseClick = 0;


		while (title_screen) { //title screen that uses user input to guide the user towards the menu layout

			c.drawRect(145, 170, 235, 100);
			c.setFont(new Font ("SansSerif", Font.BOLD, 24));
			c.drawString("PLAYZONE", 200, 200);
			c.setFont(new Font ("SansSerif", Font.BOLD, 36));
			c.drawString("Click to start", 150, 250);


			mouseClick = c.getMouseClick();
			if (mouseClick == 1) { //selection structure that uses comparison operator used in order to get the mouse click input to work
				hello_screen(); //method at line 56
				title_screen = false;
				game_on = true; 

			}

		}

		while (game_on) {

			int x = c.getDrawWidth()/2 - 100;

			c.clear();
			c.setColor(Color.RED);
			c.fillRect(x, 30, 170, 50);
			c.fillRect(x, 105, 170, 50);
			c.setColor(Color.WHITE);
			c.setFont(new Font("SansSerif", Font.PLAIN, 50));
			c.drawString("Level 1", x + 5, 70);
			c.drawString("Level 2", x + 5, 145);
			Thread.sleep(250);

			mouseClick = c.getMouseClick();
			if (mouseClick == 1 && x < c.getMouseX() && c.getMouseX() < x + 170 && c.getMouseY() > 30 && c.getMouseY() < 80 ) { //boolean operators used in order to get the mouse input to work only at a certain location in the console
				level1(); 

			} //method at line 72

			if (mouseClick == 1 && x < c.getMouseX() && c.getMouseX() < x + 170 && c.getMouseY() > 105 && c.getMouseY() < 155 ) { //boolean operators used in order to get the mouse input to work only at a certain location in the console
				level2(); 

			}//method at line 217 

		}

	}

	static void hello_screen () throws InterruptedException { //method used to create a screen where the user can type their name

		c.clear();
		//c.print("What is your name?");
		c.setFont(new Font("Helvetica", Font.PLAIN, 20));
		c.drawString("Before we start, what is your name?", 100, 100);
		c.setCursor(7, 30);
		String name = c.readLine(); //user input 
		c.clear();
		//c.print("Pleasure to meet you " + name + "! Hope you enjoy my game!");
		c.drawString("Pleasure to meet you " + name + "! Hope you enjoy my game!", 10, 100);

		Thread.sleep(1000);

	}

	static void level1 () throws InterruptedException { //first level of the game

		Console c = new Console();

		int x = c.getDrawWidth()/2 - 100;
		int y = c.getDrawHeight()/2;
		int player_x = 300;
		int player_y = 50;
		boolean level_1 = true;

		c.setFont(new Font("Helvetica", Font.BOLD, 36));

		c.clear();
		c.drawString("LEVEL 1", x, y);
		Thread.sleep(500);
		c.setFont(new Font("SansSeriff", Font.PLAIN, 20));
		c.drawString("Loading...", x + 20, y + 30); //Arithmetic operators
		Thread.sleep(250);
		c.setBackgroundColor(Color.BLUE);
		c.clear();

		int enemy_x = c.getDrawWidth();
		int enemy_x2 = 0;
		int enemy_y = c.getDrawHeight();
		int finish_x = 295;
		int finish_y = 400;
		int star_x = 100;
		int star_x2 = c.getDrawWidth() - 100;
		int star_x3 = finish_x + 15;
		int points = 0;
		//int remaining = 3 - points;

		while (level_1) { //nested selection structure

			synchronized (c) {

				c.clear();
				c.setColor(Color.YELLOW);
				c.println("Press ESC to exit the game");
				c.setCursor(25, 42);
				c.print("Points: " + points);
				c.setColor(Color.GREEN);
				c.fillOval(player_x, player_y, 20, 20);

				//player movement
				
				if (c.isKeyDown('A')) 
					player_x -= 3;

				if (c.isKeyDown('D'))
					player_x += 3;

				if (c.isKeyDown('S'))
					player_y += 3;

				if (c.isKeyDown('W'))
					player_y -= 3;

				c.setColor(Color.ORANGE);
				c.fillRect(enemy_x, 100, 75, 25);
				c.fillRect(enemy_x2, 250, 75, 25);
				c.fillRect(95, enemy_y, 35, 75);
				c.fillRect(c.getDrawWidth() - 105, enemy_y, 35, 75);
				c.setColor(Color.YELLOW);
				c.fillStar(star_x, 175, 25, 25);
				c.fillStar(star_x2, 175, 25, 25);
				c.fillStar(star_x3, 330, 25, 25);
				c.setColor(Color.RED);
				c.fillRect(finish_x, finish_y, 60, 30);
				c.setColor(Color.YELLOW);
				c.drawString("Finish", finish_x + 5, finish_y + 20);
				c.drawRect(290, 0, 360, 30);
				c.drawString("Collect 3 points and reach the finish line to complete the level", 300, 20);
				//enemy movement
				enemy_x -= 5;
				enemy_x2 += 10;
				enemy_y -= 15;

			}

			Thread.sleep(25);

			if (points == 3 && player_x < finish_x + 30 && player_y < finish_y + 30 && finish_x < player_x + 20 && finish_y < player_y + 20) { //end screen once the player wins
				c.clear();
				c.setColor(Color.YELLOW);
				c.fillRoundRect(200, 200, 240, 75, 35, 35);
				c.setColor(Color.BLACK);
				c.setFont(new Font("Helvetica", Font.BOLD, 40));
				c.drawString("YOU WON!", 210, 250);
				Thread.sleep(2000);	
				c.close();
				return; 

			}

			/*if (points != 3 && player_x < finish_x + 30 && player_y < finish_y + 30 && finish_x < player_x + 20 && finish_y < player_y + 20) {
				c.setColor(Color.RED);
				c.drawString("You do not have enough points to pass! Get " + remaining + " more point(s) to pass", 450, 300); }*/


			if (player_x < enemy_x + 75 && player_y < 125 && enemy_x < player_x + 20 && 100 < player_y + 20) { //code that makes player go back to their original position once they collide with the enemy
				player_x = 300;
				player_y = 50; 

			}

			if (c.getKeyChar() == Console.VK_ESCAPE) { //command that closes the console and causes the player to return back to level selection
				c.close();
				return; 

			}

			if (enemy_x2 >= c.getDrawWidth()) {
				enemy_x2 = 0; 

			}

			if (player_x < enemy_x2 + 75 && player_y < 275 && enemy_x2 < player_x + 20 && 250 < player_y + 20) {
				player_x = 300;
				player_y = 50; 

			}

			if (player_x < 130 && player_y < enemy_y + 75 && 95 < player_x + 20 && enemy_y < player_y + 20) {
				player_x = 300;
				player_y = 50; 

			}

			if (player_x < c.getDrawWidth() - 105 && player_y < enemy_y + 75 && c.getDrawWidth() - 105 < player_x + 20 && enemy_y < player_y + 20) {
				player_x = 300;
				player_y = 50; 

			}

			if (player_x < star_x + 25 && player_y < 200 && star_x < player_x + 20 && 175 < player_y + 20) {
				star_x = c.getDrawWidth();
				points++; 

			}

			if (player_x < star_x2 + 25 && player_y < 200 && star_x2 < player_x + 20 && 175 < player_y + 20) {
				star_x2 = c.getDrawWidth();
				points++; 

			}

			if (player_x < star_x3 + 25 && player_y < 355 && star_x3 < player_x + 20 && 330 < player_y + 20) { //code that changes the current amount of points the player has
				star_x3 = c.getDrawWidth();
				points++; 

			}

			if (enemy_x <= 0) { //code that makes the stars dissapear once collected
				enemy_x = c.getDrawWidth(); 

			}

			if (enemy_y <= 0) {
				enemy_y = c.getDrawHeight(); 

			}

		}

	}

	static void level2 () throws InterruptedException {

		Console c = new Console();

		int x = c.getDrawWidth()/2 - 100;
		int y = c.getDrawHeight()/2;
		boolean level_2 = false;
		boolean tutorial = true;
		boolean end_screen = false;

		c.setFont(new Font("Helvetica", Font.BOLD, 36));

		c.clear();
		c.drawString("LEVEL 2", x, y);
		Thread.sleep(500);
		c.setFont(new Font("SansSeriff", Font.PLAIN, 20));
		c.drawString("Loading...", x + 20, y + 30); //Arithmetic operators
		Thread.sleep(250);
		c.setBackgroundColor(Color.BLACK);
		c.clear();	

		while (true) {

			while (tutorial) {

				//if and else statement that determines whether the player is in the tutorial screen or the end screen
				
				if (end_screen == true) { //end screen that allows players to continue playing or leave the game

					c.clear();
					c.setColor(Color.WHITE);
					c.fillRoundRect(200, 200, 270, 75, 35, 35);
					c.setColor(Color.BLACK);
					c.setFont(new Font("Helvetica", Font.BOLD, 40));
					c.drawString("GAME OVER", 210, 250);
					c.setCursor(17, 25);
					c.setColor(Color.WHITE);
					c.print("Press Y to try again or N to go back to menu");

					while(c.getKeyChar() != 'y' || c.getKeyChar() != 'n') {
						if (c.getKeyChar() == 'y') {	
							tutorial = false;
							end_screen = false;
							level_2 = true;
							break;

						}

						if (c.getKeyChar() == 'n') {
							c.close();
							return;

						}
						
					}

				} 
				
				else { //tutorial screen with option to start the game
					c.setColor(Color.WHITE);
					c.setFont(new Font("Helvetica", Font.BOLD, 36));
					c.drawString("ASTEROID RUSH", x - 50, y);
					c.setCursor(16, 15);
					c.print("Dodge the asteroids and collect as many stars as you can!\n \t\t\t\t   Press Y to start");
					c.setColor(Color.YELLOW);
					c.fillStar(270, 75, 100, 100);
					c.fillStar(270, 325, 100, 100);

					if (c.getKeyChar() == 'y') {
						tutorial = false;
						level_2 = true;

					}
					
				}

			}

			while (level_2) { 

				if(tutorial == true) { 
					break;
				}

				Random rng = new Random(); //array that determines the enemies' locations
				int enemy_x[] = new int[15];
				int enemy_y[] = new int[enemy_x.length];
				int player_x = 300;
				int player_y = 400;
				int star_x = rng.nextInt(c.getDrawWidth());
				int star_y = rng.nextInt(c.getDrawHeight());
				int points = 0;


				for (int i = 0; i < enemy_x.length; i++) { 
					enemy_x[i] = rng.nextInt(c.getDrawWidth());
					enemy_y[i] = rng.nextInt(300); 

				}

				while (true) {

					synchronized (c) {

						c.clear();
						c.setColor(Color.GRAY);

						for (int i = 0; i < enemy_x.length; i++) {
							c.fillRect(enemy_x[i], enemy_y[i], 50, 50);

						}

						c.setColor(Color.RED);
						c.fillOval(player_x, player_y, 20, 20);
						c.setColor(Color.YELLOW);
						c.fillStar(star_x, star_y, 25, 25);

					}

					c.setFont(new Font("SansSeriff", Font.PLAIN, 11));
					c.drawString("Points: " + points, player_x - 10, player_y - 10);

					//player movement
					
					if (c.isKeyDown('A')) 
						player_x -= 5;

					if (c.isKeyDown('D'))
						player_x += 5;

					if (c.isKeyDown('S'))
						player_y += 5;

					if (c.isKeyDown('W'))
						player_y -= 5;

					Thread.sleep(50);

					for (int i = 0; i < enemy_x.length; i++) {

						enemy_y[i] += 7; //enemy movement

						if (enemy_y[i] >= c.getDrawHeight()) { //code that resets their locations randomly once they reach the end of the screen
							enemy_x[i] = rng.nextInt(c.getDrawWidth());
							enemy_y[i] = 0;

						}

						if (player_x < enemy_x[i] + 50 && player_y < enemy_y[i] + 50 && enemy_x[i] < player_x + 20 && enemy_y[i] < player_y + 20) { //code that leads player to end screen if the player collides with the enemy
							level_2 = false;
							end_screen = true;	
							tutorial = true; 
							c.clear();
							c.setColor(Color.WHITE);
							c.fillRoundRect(200, 200, 270, 75, 35, 35);
							c.setColor(Color.BLACK);
							c.setFont(new Font("Helvetica", Font.BOLD, 40));
							c.drawString("GAME OVER", 210, 250);
							Thread.sleep(2000);	
							break;

						}

						if (c.getKeyChar() == Console.VK_ESCAPE) { 
							c.close();
							return; 

						}

						if (player_x < star_x + 25 && player_y < star_y + 25 && star_x < player_x + 20 && star_y < player_y + 20) { //code that keeps track of points
							star_x = rng.nextInt(c.getDrawWidth());
							star_y = rng.nextInt(c.getDrawHeight());
							points++; 

						}

					}
					
					if(tutorial == true) { //code used to break the while level_2 loop 
						break;
						
					}

				}

			}

		}

	}
	
}







