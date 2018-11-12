package cl.altic.dashboardtransit.model;

import java.util.List;

public class AjaxResponseBody {

    String msg;
    List<Cuadro> result;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Cuadro> getResult() {
		return result;
	}
	public void setResult(List<Cuadro> result) {
		this.result = result;
	}
}
