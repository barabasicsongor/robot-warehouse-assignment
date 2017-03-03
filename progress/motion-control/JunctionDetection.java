import java.util.ArrayList;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import rp.config.WheeledRobotConfiguration;

public class JunctionDetection extends AbstractBehaviour {

	private final LightSensor lhSensor;
	private final LightSensor rhSensor;
	
	boolean isOnJunction = false;
	private ArrayList<ActionType> route;
	private int counter = 0;

	public JunctionDetection(WheeledRobotConfiguration _config, SensorPort _lhSensor, SensorPort _rhSensor, ArrayList<ActionType> route) {
		super(_config);
		
		lhSensor = new LightSensor(_lhSensor);
		rhSensor = new LightSensor(_rhSensor);
		
		this.route = new ArrayList<ActionType>(route);
	}

	@Override
	public boolean takeControl() {
		
		float lhValue = lhSensor.getLightValue();
		float rhValue = rhSensor.getLightValue();
		
		if(lhValue < 35 && rhValue < 35){
			isOnJunction = true;
		}
		return isOnJunction;
	}

	@Override
	public void action() {
		pilot.stop();
		
			
		//Will check what the previous move was, so it can re-orientate to always face the same orientation
		if(!(counter == (route.size() + 1))){
			
			if(counter > 0){
				ActionType previousMove = route.get(counter - 1);
				
				if(previousMove == ActionType.BACKWARDS){
					pilot.rotate(180);
					pilot.stop();
				}
				else if(previousMove == ActionType.LEFT){
					pilot.rotate(-90);
					pilot.stop();
				}
				else if(previousMove == ActionType.RIGHT){
					pilot.rotate(90);
					pilot.stop();
				}
			}
			
			if(!(counter == route.size())){
				//Iterates through the arraylist, carrying out the movements in order.
				ActionType currentMove = route.get(counter);
		
				if(currentMove == ActionType.FORWARD){
					System.out.println("forward");
					counter ++;
				}
				else if(currentMove == ActionType.BACKWARDS){
					System.out.println("backwards");
					counter++;
					pilot.rotate(180);
					pilot.stop();
				}
				else if(currentMove == ActionType.LEFT){
					System.out.println("left");
					counter++;
					pilot.rotate(90);
					pilot.stop();
				}
				else if(currentMove == ActionType.RIGHT){
					System.out.println("right");
					counter++;
					pilot.rotate(-90);
					pilot.stop();
				}
			}
		}
		System.out.println(counter);
		isOnJunction = false;
	}

}
