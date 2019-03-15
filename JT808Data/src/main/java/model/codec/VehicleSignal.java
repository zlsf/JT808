package model.codec;

/**
 * ³µÁ¾×´Ì¬
 * 
 * @author cuillgln
 *
 */
public class VehicleSignal {

	private int lowBeamSignal;
	private int highBeamSignal;
	private int rightTurnSignal;
	private int leftTurnSignal;
	private int brakeSignal;
	private int reverseSignal;
	private int mistLightSignal;
	private int positionLampSignal;
	private int hornSignal;
	private int airConditionedSignal;
	private int neutralPositionSignal;
	private int retarderRunningSignal;
	private int absRunningSignal;
	private int heaterRunningSignal;
	private int clutchSignal;

	public VehicleSignal(int bits) {
		lowBeamSignal = bits & 0x01;
		highBeamSignal = bits >> 1 & 0x01;
		rightTurnSignal = bits >> 2 & 0x01;
		leftTurnSignal = bits >> 3 & 0x01;
		brakeSignal = bits >> 4 & 0x01;
		reverseSignal = bits >> 5 & 0x01;
		mistLightSignal = bits >> 6 & 0x01;
		positionLampSignal = bits >> 7 & 0x01;
		hornSignal = bits >> 8 & 0x01;
		airConditionedSignal = bits >> 9 & 0x01;
		neutralPositionSignal = bits >> 10 & 0x01;
		retarderRunningSignal = bits >> 11 & 0x01;
		absRunningSignal = bits >> 12 & 0x01;
		heaterRunningSignal = bits >> 13 & 0x01;
		clutchSignal = bits >> 14 & 0x01;
	}

	public int getLowBeamSignal() {
		return lowBeamSignal;
	}

	public int getHighBeamSignal() {
		return highBeamSignal;
	}

	public int getRightTurnSignal() {
		return rightTurnSignal;
	}

	public int getLeftTurnSignal() {
		return leftTurnSignal;
	}

	public int getBrakeSignal() {
		return brakeSignal;
	}

	public int getReverseSignal() {
		return reverseSignal;
	}

	public int getMistLightSignal() {
		return mistLightSignal;
	}

	public int getPositionLampSignal() {
		return positionLampSignal;
	}

	public int getHornSignal() {
		return hornSignal;
	}

	public int getAirConditionedSignal() {
		return airConditionedSignal;
	}

	public int getNeutralPositionSignal() {
		return neutralPositionSignal;
	}

	public int getRetarderRunningSignal() {
		return retarderRunningSignal;
	}

	public int getAbsRunningSignal() {
		return absRunningSignal;
	}

	public int getHeaterRunningSignal() {
		return heaterRunningSignal;
	}

	public int getClutchSignal() {
		return clutchSignal;
	}

}
