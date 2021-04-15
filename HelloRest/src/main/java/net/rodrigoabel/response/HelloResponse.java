package net.rodrigoabel.response;

public class HelloResponse {
	private int id;
	private int operacion;
	private String estado;
	private String mensaje;
	
	
	
	public HelloResponse(int id, int operacion) {
		super();
		this.id = id;
		this.operacion = operacion;
		this.estado = "";
		this.mensaje = "";
	}
	
	public HelloResponse(int id) {
		super();
		this.id = id;
		this.operacion = 0;
		this.estado = "";
		this.mensaje = "";
	}
	
	public HelloResponse() {
		super();
		this.id = 0;
		this.operacion = 0;
		this.estado = "";
		this.mensaje = "";
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOperacion() {
		return operacion;
	}
	public void setOperacion(int operacion) {
		this.operacion = operacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
