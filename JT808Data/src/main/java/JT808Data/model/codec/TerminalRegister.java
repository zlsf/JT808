package JT808Data.model.codec;

/**
 * 终端注册
 * 
 * @author cuillgln
 *
 */
public class TerminalRegister {

    /**终端手机号*/
    private String terminalPhone;
    /**省*/
    private int provinceCode;
    /**市*/
    private int cityCode;
    private String producerId;
    private String terminalModel;
    private String terminalId;
    private int vehicleColor;
    private String vehicleNo;

    public TerminalRegister(String terminalPhone) {
	this.terminalPhone = terminalPhone;
    }

    public String getTerminalPhone() {
	return terminalPhone;
    }

    public int getProvinceCode() {
	return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
	this.provinceCode = provinceCode;
    }

    public int getCityCode() {
	return cityCode;
    }

    public void setCityCode(int cityCode) {
	this.cityCode = cityCode;
    }

    public String getProducerId() {
	return producerId;
    }

    public void setProducerId(String producerId) {
	this.producerId = producerId;
    }

    public String getTerminalModel() {
	return terminalModel;
    }

    public void setTerminalModel(String terminalModel) {
	this.terminalModel = terminalModel;
    }

    public String getTerminalId() {
	return terminalId;
    }

    public void setTerminalId(String terminalId) {
	this.terminalId = terminalId;
    }

    public int getVehicleColor() {
	return vehicleColor;
    }

    public void setVehicleColor(int vehicleColor) {
	this.vehicleColor = vehicleColor;
    }

    public String getVehicleNo() {
	return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
	this.vehicleNo = vehicleNo;
    }

    @Override
    public String toString() {
	return "TerminalRegister [terminalPhone=" + terminalPhone + ", provinceCode=" + provinceCode + ", cityCode="
		+ cityCode + ", producerId=" + producerId + ", terminalModel=" + terminalModel + ", terminalId="
		+ terminalId + ", vehicleColor=" + vehicleColor + ", vehicleNo=" + vehicleNo + "]";
    }

    
}
