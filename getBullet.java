//Doodle Tanks (Bullet Object)
//By: Atilla Awista, Kalea Tse, & Noor Qureshi
//Date: January 27, 2022

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class getBullet{
	//Properties
	double dblPower;
	double dblAngle;
	double dblPowerX;
	double dblPowerY;
	double dblAngleRadians;
	boolean boolLaunched;
	boolean boolHitbox=false;
	boolean boolGetCoords=true;
	boolean boolServerTank;
	int intBulletOffset=0;
	
	int intX = -20;
	int intTankX;
	int intY = 580;
	int intCurrentX = -20;
	int intCurrentY = -20;
	int intDef = +4;
	int intDef2 = -4;
	double dblTime=0;
	double dblGravity=(-9.8);
	
	String strColor = "WHITE";
	
	//Method
	///Override the method in JPanel
	///To draw this APanel differently
	///JPanel by default draws a grey background
	///APanel will draw whatever we want it to
	public void nextPos(){
		dblTime=dblTime+(10/60.0);
		
		dblAngleRadians=Math.toRadians(dblAngle);
		dblPowerX=((dblPower)*(Math.cos(dblAngleRadians)));
		dblPowerY=(1*((dblPower)*(Math.sin(dblAngleRadians))));
		//Uses kinematics to calculate cannonball velocity in the X and Y
		
		if(boolServerTank==true){
			intBulletOffset=40;
		}else{
			intBulletOffset=-40;
		}
		//This makes it so the cannonball fires out of the barrel side of the tank depending if its a client or server tank
		
		//d = v*t
		intX=((int)(dblPowerX*dblTime))+(intTankX+intBulletOffset);
		//Uses kinematics to calculate cannonball displacement in the X (displacement= velocity*time)
		
		//d = Vi*t + 1/2*a*t^2
		intY=((((int)((dblPowerY*dblTime)+(0.5*dblGravity*(Math.pow(dblTime, 2)))))*-1)+560);
		//Uses kinematic equation to calculate cannonball displacement in the Y
		
		if(boolLaunched==false){
			if(boolHitbox==true && boolGetCoords==true){
				//intCurrentX=intX;
				//intCurrentY=intY;
				intCurrentX=-20;
				intCurrentY=-20;
				boolGetCoords=false;
			}
			//If the ball hits something (hitbox), then gather the coordnates where it hits and change boolGetCoords to false, this ensures that
			//the coordnates are only gathered ONE time, otherwise it would just keep updating the coords and the ball would keep moving
			intX=intCurrentX;
			intY=intCurrentY;
			dblGravity=0;
			dblPowerY=-20;
			dblPowerX=-20;
		}
		//If the ball isn't considered "launched", stop the ball from moving
	}
	/**
	 * Constructs the bullet that tanks shoot and draws them to the screen.
	 * It draws them travelling in an arc from a specific starting position
	 * To a specific end position.
	 */
	public void drawIt(Graphics g){
		g.setColor(Color.DARK_GRAY);
		if(dblPowerX ==0){
			g.setColor(Color.RED);
		}
		//Once the cannon stops moving, turn it red as a visual indicator (for testing purposes)
		g.fillOval(intX, intY, 20, 20);
		this.nextPos();
	}
	//This function visually draws out the cannonball
	
	//Constructor
	/**
	 * Constructs the bullet that tanks shoot and draws them to the screen.
	 * It draws them travelling in an arc from a specific starting position
	 * To a specific end position. These are found based on the X position 
	 * of the tank, how much power the gun launches from, what angle to shoot at
	 * and if the tank gun has been fired.
	 */
	public getBullet(int intTankX, double dblPower, double dblAngle, boolean boolLaunched, boolean boolServerTank){
		super();
		this.intTankX=intTankX;
		this.dblPower=dblPower;
		this.dblAngle=dblAngle;
		this.boolLaunched=boolLaunched;
		this.boolServerTank=boolServerTank;
	}
	///Now the APanel and JPanel are exactly the same

}
